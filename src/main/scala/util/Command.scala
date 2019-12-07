package util

import java.lang.ModuleLayer.Controller

import main.scala.model.Card
import main.scala.model.Map

import supervisor.supervisor

trait Command {
  var x:Int
  var y:Int
  def execute(x:Int, y:Int,card: Card,map: Map): Int
  def undo(card: Card,map: Map): Unit
}
