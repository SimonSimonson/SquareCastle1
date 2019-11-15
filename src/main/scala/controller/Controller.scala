package controller
import main.scala.model.{Card, Map, Player}
import main.scala.TUI.TUI
import main.scala.util.Observable

class Controller extends Observable{



  def start(p1: Player, p2: Player, runden: Int): Unit= {

    val map = new Map(5,4)
    map.print()

    for(i <-  0 until runden){
      Thread.sleep(500)
      val card1 = Kartezeigen(p1)
      Optionen(card1, map, p1)
      map.print()

      Thread.sleep(500)
      val card2 = Kartezeigen(p2)
      Optionen(card2, map, p2)
      map.print()
    }
    notifyObservers(p1.Punkte.toString)
    //println(p1.Punkte)
    notifyObservers(p2.Punkte.toString)
    //println(p2.Punkte)
    if(p1.Punkte > p2.Punkte)
      notifyObservers(Console.RED + p1.toString()+" GEWINNT")
      //println(Console.RED + p1.toString()+" GEWINNT")
    else if(p2.Punkte > p1.Punkte)
      notifyObservers(Console.RED + p2.toString()+" GEWINNT")
      //println(Console.RED + p2.toString()+" GEWINNT")
    else{
      notifyObservers("unentschieden")
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
    notifyObservers(Console.RED + "Spieler " + player.toString() + " ist an der Reihe")
    //println(Console.RED + "Spieler " + player.toString() + " ist an der Reihe")
    notifyObservers(Console.WHITE)
    //print(Console.WHITE)
    card.print()
    return card
  }





  def Optionen(card: Card, map: Map, player: Player): Unit = {
    notifyObservers(Console.BLUE + "r.... rechts rum rotieren"+ Console.CYAN + "  l.... links rum rotieren"+
      Console.MAGENTA + "  i x y.... Einfügen bei (x,y)"+ Console.YELLOW + "  wait.... eine runde aussetzen"+
      Console.BLACK + "  tipp .... zeigt wo man anlegen kann"+ Console.RED + "  exit .... beenden")

    var a = true
    while (a) {
      val x = scala.io.StdIn.readLine().toString
      val array = x.split(" +")
      if (array(0).equals("r")) {
        card.rotateRight()
        card.print()
      } else if (array(0).equals("wait")) {
        a = false
        return
      } else if (array(0).equals("l")) {
        card.rotateLeft()
        card.print()
      } else if (array(0).equals("i")) {
        if (map.Setcard(card, array(1).toInt, array(2).toInt) == 1) {
          player.addCard(card)
          val punkte = card.getAngelegte()
          notifyObservers("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte")
          //println("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte")
          player.addPoints(punkte)
          //println(player.toString() +"  :"+ player.Punkte)
          a = false
          return
        } else {
          notifyObservers("Die Karte passt nicht")
          //println("Die Karte passt nicht")
        }
      } else if (array(0).equals("tipp")) {
        map.tipp(card)
      }
    }
  }
  def getPoints(player1: Player, player2: Player): (Int,Int) ={
    val points1 = player1.getPoints()
    println(points1)
    val points2 = player2.getPoints()
    println(points2)



    return (points1,points2)
  }

}
