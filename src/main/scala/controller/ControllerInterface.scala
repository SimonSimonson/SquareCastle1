package controller

import java.awt.image.BufferedImage

import controller.states.State
import main.scala.model.Card
import model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  var state: State
  var befehl: String
  val invoker: Invoker

  def changeState(state: State): Boolean

  def Calculatebot(bot: KIInterface, card: CardInterface, map: MapInterface): Boolean

  def showCard(player: PlayerInterface): Card

  def Options(card: CardInterface, map: MapInterface, player: PlayerInterface): Int

  def getPoints(player1: PlayerInterface, player2: PlayerInterface): (Int, Int)

  def tipp(card: CardInterface, map: MapInterface): Array[Array[Int]]

  def print(map: MapInterface): Boolean

  def printpunkte(p1: PlayerInterface, p2: PlayerInterface, bot: KIInterface, bot2: KIInterface): Boolean

  def rotatePic(rot: Int, image: BufferedImage): BufferedImage
}








