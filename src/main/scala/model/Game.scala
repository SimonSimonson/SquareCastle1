package main.scala.model
import main.scala.model.Card
import main.scala.model.Map
case class Game() {

  def start(p1: Player, p2: Player, runden: Int): Unit= {
    val map = new Map(3,3)
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
    //PUNKTE AUSGEBEN, AUSWERTEN DER PUNKTE
    //val punkte = getPoints(p1,p2)
    if(punkte._1 > punkte._2)
      println(Console.RED + p1.toString()+" GEWINNT")
    else if(punkte._1 > punkte._2)
      println(Console.RED + p2.toString()+" GEWINNT")
    else{
      println(Console.RED + "UNENTSCHIEDEN")

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
    println(Console.RED + "Spieler " + player.toString() + " ist an der Reihe")
    print(Console.WHITE)
    card.print()
    return card
  }

/*
  //Fürs Interface!
  def isNumber(string: String): Boolean ={
    //val x = scala.io.StdIn.readLine().toString
    for (c <- string){
      c.isDigit
      return true
    }
    return false
  }
*/


  def Optionen(card: Card, map: Map, player: Player): Unit = {
    println(Console.RED + "r.... rotieren  ,  i x y.... Einfügen bei (x,y)  ,  wait .... Warten")
    var a = true
    while (a) {
      val x = scala.io.StdIn.readLine().toString
      val array = x.split(" +")
      println(array(0))
      if (array(0).equals("r")) {
        card.rotateRight()
        card.print()
      } else if (array(0).equals("wait")){
        a = false
        return
      } else if (array(0).equals("l")){
        card.rotateLeft()
        card.print()
      } else if (array(0).equals("i")) {
        if(map.Setcard(card, array(1).toInt, array(2).toInt)== 1){
          player.addCard(card)
          player.addPoints(card.getAngelegte())
          a = false
          return
        }else {
          println("Fehler beim einfügen")
        }
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