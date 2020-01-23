package gamecontrol.controller.commands

import gamemodel.model.{CardInterface, MapInterface}

import scala.util.Try

trait Command {
  var x:Int
  var y:Int
  def execute(x:Int, y:Int, card: CardInterface, map: MapInterface): Try[Int]
  def undo(card: CardInterface,map: MapInterface): Boolean
}
