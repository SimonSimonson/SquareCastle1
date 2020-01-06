package main.scala.model

import scala.collection.mutable.ListBuffer
case class Player(name: String ) {
    var Points = 0
    var EasyMode = false

    def addPoints(punkte: Int ): Unit ={
      Points += punkte
    }
    def getPoints(): Int={
        return Points
    }
    def EasyMode(boolean: Boolean): Int={
        this.EasyMode = boolean
        1
    }


    override def toString(): String = name

}
