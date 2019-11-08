package main.scala.model

import scala.collection.mutable.ListBuffer

case class Player(name: String ) {
    val karten = new ListBuffer[Card]()


    def getPoints(): Int={
        var sum = 0
        karten.toList.foreach{getAngelegte()}


        return sum
    }


    override def toString(): String = name

}
