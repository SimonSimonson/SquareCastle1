package model

import java.util

import main.scala.model.{Card, Map}

import scala.util.Try

trait PlayerInterface {
  var Points: Int
  def addPoints(punkte: Int ): Unit
  def getPoints(): Int
}
// 3 andere noch

trait CardInterface {
  var mysides: Array[Int]
  val none: Array[CardInterface]
  val roads: Array[CardInterface]
  val castle: Array[CardInterface]
  def getAngelegteR(kind: Int, l: Int, prev: CardInterface, list: util.ArrayList[Card]): Int
  def contains(list: util.ArrayList[Card], card: CardInterface): Boolean
  def getAngelegte(): Int
  def rotateRight(): Boolean
  def rotateLeft(): Boolean
  def getantipos(pos: Int): Int
  def passt(card: CardInterface, pos1: Int, pos2: Int): Boolean
  def anlegen(pos: Int, karte: CardInterface): Boolean
  def cleansides(pos: Int): Boolean
  def cleanall(): Boolean
  def toString: String
}
trait MapInterface{
  val field:Array[Array[CardInterface]]

  def getmy(): Int
  def getmx(): Int
  def check(card : CardInterface, x:Int, y : Int): Boolean
  def getpointswithoutputting(card: CardInterface, x:Int, y:Int): Int
  def setRandom(card: CardInterface): Boolean
  def Setcard(card : CardInterface, x:Int , y : Int): Try[Int]
  def cleanaround(x:Int,y:Int)
  def isFull(): Boolean
}
trait KIInterface {
  var Punkte : Int
  def addPoints(punkte: Int ): Boolean
  def getPoints(): Int
  def anlegen(map: MapInterface, card: CardInterface): (Int, Int, Int)
  override def toString: String = "Bot"
}