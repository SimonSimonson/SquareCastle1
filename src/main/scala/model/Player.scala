package main.scala.model

import scala.collection.mutable.ListBuffer
case class Player(name: String ) {
    var Punkte = 0
    var EasyMode = false

    def addPoints(punkte: Int ): Unit ={
      Punkte += punkte
    }
    def getPoints(): Int={
        return Punkte
    }
    def EasyMode(boolean: Boolean): Int={
        this.EasyMode = boolean
        1
    }


    override def toString(): String = name

}
