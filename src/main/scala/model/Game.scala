package main.scala.model
import main.scala.model.Card
import main.scala.model.Map
case class Game () {

  def start(p1: Player, p2: Player, runden: Int): Unit= {
    val map = new Map(10,5)
    map.print()

    for(i <-  0 to runden){
      Thread.sleep(500)
      val card1 = Kartezeigen(p1)
      Optionen(card1, map)
      map.print()

      Thread.sleep(500)
      val card2 = Kartezeigen(p2)
      Optionen(card2, map)
      map.print()
    }
    /*
    val karte1 = new Card(0, 2, 1, 2)
    val karte2 = new Card(0, 2, 1, 2)
    val karte3 = new Card(0, 2, 1, 2)

    //karte2.rotate()
    karte2.rotate()
    //karte2.rotate()

    var er = map.Setcard(karte1,map.mid._1,map.mid._2)
    er = map.Setcard(karte3,map.mid._1+1,map.mid._2-1)

    if(er == -1)
      println("Karte konnte nicht gesetzt werden")
    map.print()


    er = map.Setcard(karte2,map.mid._1,map.mid._2-1)

    if(er == -1)
      println("Karte konnte nicht gesetzt werden")
    map.print()

     */
  }
  def RandomCard(): Card= {
    val r = scala.util.Random
    var s1 = r.nextInt(3)
    var s2 = r.nextInt(3)
    var s3 = r.nextInt(3)
    var s4 = r.nextInt(3)

    return new Card(s1,s2,s3,s4)
  }
  def Kartezeigen(player: Player): Card ={
    val card = RandomCard()
    println(Console.RED + "spieler " + player.toString() + "hier ist deine erste Karte")
    card.print()
    return card
  }

  def Optionen(card: Card, map: Map): Unit ={
    println(Console.RED+ "r.... rotieren  ,  i x y.... Einfügen bei (x,y)  ,  exit .... beenden")

    val x = scala.io.StdIn.readLine().toString
    val array = x.split("//s")
    while(true) {
      if (array(0).equals("r")) {
        card.rotate()
        card.print()
      } else if (array(0).equals("i")) {
        map.Setcard(card, array(1).toInt, array(2).toInt)
        return

      }
    }




  }
}