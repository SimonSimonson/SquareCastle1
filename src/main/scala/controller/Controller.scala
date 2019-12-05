package controller
import main.scala.model.{Card, Map, Player}
import main.scala.util.Observable
import mainn.scala.model.KI
import util.State

class Controller extends Observable{

  var state:State = _
  var befehl:String = _

  def changeState(state: State):Unit={
    this.state = state
  }

  def start(p1: Player, p2: Player, setMap: Map, runden: Int): Unit= {

    val map = setMap
    print(map)

    for(i <-  0 until runden){
      Thread.sleep(500)
      var card1 = Kartezeigen(p1)
      //if(decoratedplayer).noob = true
      //card1 = selectcard()
      Optionen(card1, map, p1)
      print(map)
      notifyObservers("Puntkte von Spieler " + p1 + ": " + p1.Punkte.toString(), 0)
      notifyObservers("Puntkte von Spieler " + p2 + ": " + p2.Punkte.toString(), 0)

      Thread.sleep(500)
      val card2 = Kartezeigen(p2)
      Optionen(card2, map, p2)
      print(map)
      notifyObservers("Puntkte von Spieler " + p1 + ": " + p1.Punkte.toString(), 0)
      notifyObservers("Puntkte von Spieler " + p2 + ": " + p2.Punkte.toString(), 0)
    }
    notifyObservers(p1.Punkte.toString,0)
    //println(p1.Punkte)
    notifyObservers(p2.Punkte.toString,0)
    //println(p2.Punkte)
    if(p1.Punkte > p2.Punkte)
      notifyObservers(Console.RED + "SPIELER " + p1.toString()+" GEWINNT",0)
      //println(Console.RED + p1.toString()+" GEWINNT")
    else if(p2.Punkte > p1.Punkte)
      notifyObservers(Console.RED + "SPIELER " + p2.toString()+" GEWINNT",0)
      //println(Console.RED + p2.toString()+" GEWINNT")
    else{
      notifyObservers("UNENTSCHIEDEN",0)
      //println(Console.RED + "UNENTSCHIEDEN")

    }
  }

  def startbot(p1:Player,bot: KI, setMap: Map, runden: Int): Unit= {
    val map = setMap

    print(map)

    for (i <- 0 until runden) {
      Thread.sleep(500)

      val card1 = Kartezeigen(p1)
      Optionen(card1, map, p1)
      print(map)
      notifyObservers("Puntkte von Spieler " + p1 + ": " + p1.Punkte.toString(), 0)
      notifyObservers("Puntkte von Bot " + bot + ": " + bot.Punkte.toString(), 0)

      Thread.sleep(500)
      val card = RandomCard()
      notifyObservers("Karte von Bot:",0)
      printcard(card)
      //wait(500)
      Calculatebot(bot,card,map)
      print(map)
      notifyObservers("Puntkte von Spieler " + p1 + ": " + p1.Punkte.toString(), 0)
      notifyObservers("Puntkte von Bot " + bot + ": " + bot.Punkte.toString(), 0)

    }


    if(p1.Punkte > bot.Punkte)
      notifyObservers(Console.RED + "SPIELER " + p1.toString()+" GEWINNT",0)
    //println(Console.RED + p1.toString()+" GEWINNT")
    else if(bot.Punkte > p1.Punkte)
      notifyObservers(Console.RED + "SPIELER " + bot.toString()+" GEWINNT",0)
    //println(Console.RED + p2.toString()+" GEWINNT")
    else{
      notifyObservers("UNENTSCHIEDEN",0)
      //println(Console.RED + "UNENTSCHIEDEN")

    }
  }
  def Calculatebot(bot: KI, card: Card,map:Map): Unit ={
    val data = bot.anlegen(map, card)

    for(i <- 0 until data._3){
      notifyObservers("rotieren",0)
      card.rotateRight()
    }
    if(data._1 < 0 || data._2 < 0 || data._3 < 0) {
      //map.setRandom(card)
      notifyObservers("beep boop ich kann nicht anlegen",0);
      return
    }
      notifyObservers("Bot legt auf " +data._1 +" "+ data._2,0)
      card.cleanall()
      map.Setcard(card,data._1,data._2)
      bot.addPoints(card.getAngelegte())
  }

  def RandomCard(): Card= {
    val r = scala.util.Random
    val s1 = r.nextInt(3)
    val s2 = r.nextInt(3)
    val s3 = r.nextInt(3)
    val s4 = r.nextInt(3)

    return Card(s1,s2,s3,s4)
  }

  def Kartezeigen(player: Player): Card ={
    val card = RandomCard()
    if(player == null)
      notifyObservers(Console.RED + "BOT ist an der Reihe",0)
    else
      notifyObservers(Console.RED + "Spieler " + player.toString() + " ist an der Reihe",0)
    //println(Console.RED + "Spieler " + player.toString() + " ist an der Reihe")

    notifyObservers(Console.WHITE,0)
    //print(Console.WHITE)

    printcard(card)
    if(player!= null)
      notifyObservers(Console.BLUE + "r.... rechts rum rotieren"+ Console.CYAN + "  l.... links rum rotieren"+
        Console.MAGENTA + "  i x y.... Einfügen bei (x,y)"+ Console.YELLOW + "  wait.... eine runde aussetzen"+
        Console.BLACK + "  tipp .... zeigt wo man anlegen kann"+ Console.RED + "  exit .... beenden",0)

    return card
  }
  /*
  def selectCard(player:Player): Card ={
    val card1 = RandomCard()
    val card2 = RandomCard()
    val card3 = RandomCard()

    notifyObservers(Console.RED + "Spieler " + player.toString() + " ist an der Reihe",0)
    notifyObservers(Console.WHITE,0)

    printcard(card1)
    printcard(card2)
    printcard(card3)

    notifyObservers(Console.RED + "Wähle eine Karte aus (Bsp.: Gib für die erste Karte eine 1 ein!", 0)
    val x = scala.io.StdIn.readLine().toString

    x match {
      case "1" =>
        printcard(card1)
        return card1
      case "2" =>
        printcard(card2)
        return card2
      case "3" =>
        printcard(card3)
        return card3
    }

  }
  */


  def Optionen(card: Card, map: Map, player: Player): Unit = {

    var a = true
    while (a) {
      //auslagern durch neuen parameter der String
      val x = befehl
      val array = x.split(" +")

      if (array(0).equals("r")) {
        card.rotateRight()
        printcard(card)
      } else if (array(0).equals("wait")) {
        a = false
        return

      } else if (array(0).equals("l")) {
        card.rotateLeft()
        printcard(card)

      } else if (array(0).equals("i")) {

        if (map.Setcard(card, array(1).toInt, array(2).toInt) == 1) {

          val punkte = card.getAngelegte()
          notifyObservers("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte",0)
          //println("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte")
          player.addPoints(punkte)
          //println(player.toString() +"  :"+ player.Punkte)
          a = false
          return
        } else {

          notifyObservers("Die Karte passt nicht",0)
          //println("Die Karte passt nicht")
        }

      } else if (array(0).equals("tipp")) {
        tipp(card, map)
      }
    }
  }

  def getPoints(player1: Player, player2: Player): (Int,Int) ={
    val points1 = player1.getPoints()
    //println(points1)
    val points2 = player2.getPoints()
    //println(points2)



    return (points1,points2)
  }

  def tipp(card: Card, map: Map): Boolean = {

    println(Console.WHITE)
    for (iy <- 0 until map.getmy()) {
      for (zeile <- 0 to 5) {
        for (ix <- 0 until map.getmx()) {
          if(map.field(ix)(iy) != null)
            printline(zeile,map.field(ix)(iy))
          else{
            if(map.pruefen(card,ix,iy))
              highlight(zeile)
            else
              nullprint(zeile)
          }
        }
        notifyObservers("",0)
      }

    }
    true
  }
  def printline(zeile: Int, card: Card): Unit={
    zeile match{
      case 0 => notifyObservers(" _________ ",1)
      case 1 => notifyObservers("|         |",1)
      case 2 => notifyObservers("|    " + card.mysides(0) + "    |",1)
      case 3 => notifyObservers("| "+card.mysides(3)+"     "+ card.mysides(1)+" |",1)
      case 4 => notifyObservers("|    " + card.mysides(2) + "    |",1)
      case 5 => notifyObservers("|_________|",1)

    }
  }

  def printcard(card: Card): Null ={
    notifyObservers(" _________", 0)
    notifyObservers("|         |",0)
    notifyObservers("|    " + card.mysides(0) + "    |",0)
    notifyObservers("| "+card.mysides(3)+"     "+ card.mysides(1)+" |",0)
    notifyObservers("|    " + card.mysides(2) + "    |",0)
    notifyObservers("|_________|",0)

    null
  }
  def nullprint(zeile: Int): Unit = {
    zeile match {

      case 0 => notifyObservers(" _________ ",1)
      case 1 => notifyObservers("|         |",1)
      case 2 => notifyObservers("|         |",1)
      case 3 => notifyObservers("|         |",1)
      case 4 => notifyObservers("|         |",1)
      case 5 => notifyObservers("|_________|",1)

    }
  }

  def highlight(zeile: Int): Unit = {
    zeile match {

      case 0 => notifyObservers(Console.YELLOW + " _________ ",1)
      case 1 => notifyObservers(Console.YELLOW + "|         |",1)
      case 2 => notifyObservers(Console.YELLOW + "|         |",1)
      case 3 => notifyObservers(Console.YELLOW + "|         |",1)
      case 4 => notifyObservers(Console.YELLOW + "|         |",1)
      case 5 => notifyObservers(Console.YELLOW + "|_________|",1)

    }
    printf(Console.WHITE)
  }

  def print(map: Map): Unit ={
    notifyObservers(Console.WHITE,0)
    for(iy <- 0 until map.getmy()){
      for(zeile <- 0 to 5){
        for(ix <- 0 until map.getmx()){

          if(map.field(ix)(iy)==null)
            nullprint(zeile)
          else
            printline(zeile,map.field(ix)(iy))
        }
        notifyObservers("",0)
      }

    }

  }
}
