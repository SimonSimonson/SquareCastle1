package main.scala.model

import model.PlayerInterface

case class Player(name: String ) extends PlayerInterface{
    var Points = 0

    def addPoints(punkte: Int ): Unit ={
      Points += punkte
    }
    def getPoints(): Int={
        return Points
    }


    override def toString(): String = name

}
