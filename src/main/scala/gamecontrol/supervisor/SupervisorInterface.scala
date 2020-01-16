package gamecontrol.supervisor

import gamecontrol.controller.{Controller, ControllerInterface}
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

import scala.swing.Publisher

trait SupervisorInterface extends Publisher{
  var controller:ControllerInterface
  var p1:PlayerInterface
  var p2:PlayerInterface
  var bot:KIInterface
  var bot2:KIInterface
  var map:MapInterface
  var rounds:Int
  var botyesno:Boolean
  var card:CardInterface
  var playersturn:PlayerInterface
  var state:Boolean

  def otherplayer():Boolean
  def newRound(): Int
  def newRoundactive(): Int
  def showPoints(): Boolean
  def endgame(): String

}
