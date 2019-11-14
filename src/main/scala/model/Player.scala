package main.scala.model

import scala.collection.mutable.ListBuffer
case class Player(name: String ) {
    var Punkte = 0
    val karten = new ListBuffer[Card]()

    def addCard(card: Card): Unit ={
        karten.addOne(card)
    }
    def addPoints(punkte: Int ): Unit ={
      Punkte += punkte
    }
    def getPoints(): Int={
        if(karten.isEmpty)
            return -1

        var sum = 0
        karten.foreach(c => sum += c.getAngelegte())
        return sum
    }


    override def toString(): String = name

}
