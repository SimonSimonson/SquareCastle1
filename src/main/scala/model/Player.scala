package main.scala.model

import scala.collection.mutable.ListBuffer
case class Player(name: String ) {
    var Punkte = 0

    def addPoints(punkte: Int ): Unit ={
      Punkte += punkte
    }
    def getPoints(): Int={
        return Punkte
    }


    override def toString(): String = name

}
