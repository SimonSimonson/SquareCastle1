package mainn.scala.model
import main.scala.model.{Card, Map, Player}
import scala.language.postfixOps

case class KI(){
  var Punkte = 0



  def addPoints(punkte: Int): Unit = {
    Punkte += punkte
  }


  def getPoints(): Int = {

    Punkte
  }
  // gibt array mit Punkten für jedes Feld zurück
  def punktefeld(map:Map, card: Card): (Array[Array[Int]])={

    var punkte = Array.ofDim[Int](map.field.length, map.field(1).length)

    for (y <- 0 until map.field(1).length) {
      for (x <- 0 until map.field.length) {
        //prüft und legt an

          //geht nicht weil karte nicht angelegt ist
          punkte(x)(y) = map.getpunkteohneanlegen(card,x,y)


      }
    }
    (punkte)

  }

//gibt maximum des arrays mit index zurück
  def getxy(array: Array[Array[Int]],int: Int): (Int,Int)={
    for (y <- 0 until Array(0).length) {
      for (x <- 0 until array.length) {
        if (array(x)(y) == int) {
          (x,y)
        }
      }
    }
    (-1,-1)
  }

  //GIBT DIE X,Y KOORDINATEN DER BESTEN FUNKTION UND DIE ANZAHL DER ROLLS ZURÜCK   FEEEHLER: falsche punktezahlen(immer+2) in arrays
  def anlegen(map:Map, card: Card):(Int,Int,Int)= {
    var all = Array.ofDim[Array[Array[Int]]](4)
    val list = List()
    all(0)= punktefeld(map,card)
    card.rotateRight()
    println(all(0).flatten.max)

    all(1) = punktefeld(map,card)
    card.rotateRight()
    println(all(1).flatten.max)

    all(2) = punktefeld(map,card)
    card.rotateRight()
    println(all(2).flatten.max)

    all(3) = punktefeld(map,card)
    card.rotateRight()
    println(all(3).flatten.max)

    var opt = List(all(0).flatten.max,all(1).flatten.max, all(2).flatten.max,all(3).flatten.max)
    println("LISTE DER PUNKTE : "+ opt)
    var tmp = opt.sorted
    println("LISTE DER PUNKTE : "+ tmp)
    var rolls = opt.indexOf(tmp(3))
    println("ANZAHL ROLLEN IST " + rolls)


    val a = all(rolls).toList :: list
    var i = 0
    for(index <- all(rolls)){
      var iy = 0
      for(index2 <- all(rolls)(i)){
        if(index2 == tmp(3))
          return (i,iy,rolls)
        iy +=1
      }
      i += 1;
    }
    (-1,-1,-1)
  }

  override def toString(): String = "01001101"

}