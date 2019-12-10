package controller
import controller.commands.layCommand
import main.scala.model.{Card, Map, Player}
import main.scala.util.Observable
import mainn.scala.model.KI
import util.{Invoker, State}

class Controller extends Observable{

  var state:State = _
  var befehl:String = _
  val invoker:Invoker = new Invoker(this)

  def changeState(state: State):Boolean={
    if(state== null)
      return false
    this.state = state
    true
  }

  def Calculatebot(bot: KI, card: Card,map:Map):Unit ={
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

//return 1: normaler fall, return -1: fehler fall,  return 2 : spieler darf nocheinmal
  def Optionen(card: Card, map: Map, player: Player): Int = {

      //auslagern durch neuen parameter der String
      val x = befehl
      val array = x.split(" +")

      if (array(0).equals("r")) {
        card.rotateRight()
        printcard(card)
        return 2
      } else if (array(0).equals("wait")) {
        //muss implementiert werden
        return 2

      } else if (array(0).equals("l")) {
        card.rotateLeft()
        printcard(card)
        return 2

      } else if (array(0).equals("i")) {
        if (invoker.ExecuteCommand(new layCommand,array(1).toInt,array(2).toInt,card,map) == 1) {

          val punkte = card.getAngelegte()
          notifyObservers("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte",0)
          //println("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte")
          player.addPoints(punkte)
          //println(player.toString() +"  :"+ player.Punkte)
          return 1
        } else {
          notifyObservers("Die Karte passt nicht",0)
          //println("Die Karte passt nicht")
          return 2
        }
      } else if (array(0).equals("undo")) {
        invoker.Undo(card,map)
        print(map)
        return 2
      } else if (array(0).equals("tipp")) {
        tipp(card, map)
        return 2
      }

    notifyObservers("BEFEHL NICHT ERKANNT",0)
    2
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
  def printpunkte(p1:Player, p2:Player, bot:KI): Unit ={
    notifyObservers(Console.RED+"Punkte von Spieler " + p1.toString() +":  "+ p1.Punkte,0 )
    if(p2 != null)
      notifyObservers(Console.RED+"Punkte von Spieler " + p2.toString() +":  "+ p2.Punkte,0 )
    else
      notifyObservers(Console.RED+"Punkte von BOT " + bot.toString() +":  "+ bot.Punkte,0 )

  }
}
