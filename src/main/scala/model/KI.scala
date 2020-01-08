package main.scala.model

import main.scala.model.{Card, Map, Player}
import scala.language.postfixOps

case class KI() {
  var Punkte = 0


  def addPoints(punkte: Int): Boolean = {
    if (punkte < 0)
       return false
    Punkte += punkte
    true
  }


  def getPoints(): Int = {

    Punkte
  }

  // gibt array mit Punkten für jedes Feld zurück
  def punktefeld(map: Map, card: Card): (Array[Array[Int]]) = {

    var punkte = Array.ofDim[Int](map.field.length, map.field(1).length)

    for (y <- map.field(1).indices) {
      for (x <- map.field.indices) {

        //prüft und legt an

        //geht nicht weil karte nicht angelegt ist
        punkte(x)(y) = map.getpointswithoutputting(card, x, y)
        card.cleanall()
        //println("X:  "+x+"  Y:   "+y+"  Punkte:   "+punkte(x)(y))
      }
    }
    (punkte)

  }

  //GIBT DIE X,Y KOORDINATEN DER BESTEN FUNKTION UND DIE ANZAHL DER ROLLS ZURÜCK   FEEEHLER: falsche punktezahlen(immer+2) in arrays
  def anlegen(map: Map, card: Card): (Int, Int, Int) = {
    var all = Array.ofDim[Array[Array[Int]]](4)
    val list = List()
    all(0) = punktefeld(map, card)
    card.rotateRight()
    //println(all(0).flatten.max)
    //card.cleanall()

    all(1) = punktefeld(map, card)
    card.rotateRight()
    //println(all(1).flatten.max)
    //card.cleanall()

    all(2) = punktefeld(map, card)
    card.rotateRight()
    //println(all(2).flatten.max)
    //card.cleanall()

    all(3) = punktefeld(map, card)
    card.rotateRight()

    val opt = List(all(0).flatten.max, all(1).flatten.max, all(2).flatten.max, all(3).flatten.max)
    //println("LISTE DER PUNKTE : " + opt)
    val tmp = opt.sorted
    val rolls = opt.indexOf(tmp(3))
    if(opt(0) == 0 && opt(3) == 0)
      return (-1, -1, -1)

    val a = all(rolls).toList :: list
    var i = 0
    var iy = 0
    for (index <- all(rolls)) {
      iy = 0
      for (index2 <- all(rolls)(i)) {
        if (index2 == tmp(3))
          return (i, iy, rolls)
        iy += 1
      }
      i += 1;
    }
    /*//Legt, wenn egal ist wie oft er die Karte dreht, irgendwo an
    if(opt(0)==opt(3))
      return (i, iy, rolls)*/
    (-1, -1, -1)
  }

  override def toString(): String = "BOT"

}