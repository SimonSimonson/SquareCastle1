package controller
import main.scala.model.{Card, Map, Player}
import main.scala.TUI.TUI
import main.scala.util.Observable

class Controller extends Observable{



  def start(p1: Player, p2: Player, runden: Int): Unit= {

    val map = new Map(5,4)
    print(map)

    for(i <-  0 until runden){
      Thread.sleep(500)
      val card1 = Kartezeigen(p1)
      Optionen(card1, map, p1)
      print(map)

      Thread.sleep(500)
      val card2 = Kartezeigen(p2)
      Optionen(card2, map, p2)
      print(map)
    }
    notifyObservers(p1.Punkte.toString,0)
    //println(p1.Punkte)
    notifyObservers(p2.Punkte.toString,0)
    //println(p2.Punkte)
    if(p1.Punkte > p2.Punkte)
      notifyObservers(Console.RED + p1.toString()+" GEWINNT",0)
      //println(Console.RED + p1.toString()+" GEWINNT")
    else if(p2.Punkte > p1.Punkte)
      notifyObservers(Console.RED + p2.toString()+" GEWINNT",0)
      //println(Console.RED + p2.toString()+" GEWINNT")
    else{
      notifyObservers("unentschieden",0)
      //println(Console.RED + "UNENTSCHIEDEN")

    }
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
    notifyObservers(Console.RED + "Spieler " + player.toString() + " ist an der Reihe",0)
    //println(Console.RED + "Spieler " + player.toString() + " ist an der Reihe")
    notifyObservers(Console.WHITE,0)
    //print(Console.WHITE)
    printcard(card)
    return card
  }





  def Optionen(card: Card, map: Map, player: Player): Unit = {
    notifyObservers(Console.BLUE + "r.... rechts rum rotieren"+ Console.CYAN + "  l.... links rum rotieren"+
      Console.MAGENTA + "  i x y.... Einfügen bei (x,y)"+ Console.YELLOW + "  wait.... eine runde aussetzen"+
      Console.BLACK + "  tipp .... zeigt wo man anlegen kann"+ Console.RED + "  exit .... beenden",0)

    var a = true
    while (a) {
      val x = scala.io.StdIn.readLine().toString
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
          player.addCard(card)
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

  def tipp(card: Card, map: Map): Unit = {
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
    /*
            x = x + 1
            if (x == mx) {
              y = y + 1
              x = 0
            }*/
  }
  def printline(zeile: Int, card: Card): Unit={
    zeile match{
      case 0 => printf(" _________ ")
      case 1 => printf("|         |")
      case 2 => printf("|    " + card.mysides(0) + "    |")
      case 3 => printf("| "+card.mysides(3)+"     "+ card.mysides(1)+" |")
      case 4 => printf("|    " + card.mysides(2) + "    |")
      case 5 => printf("|_________|")

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
