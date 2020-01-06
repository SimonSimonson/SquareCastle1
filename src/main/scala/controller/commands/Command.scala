package controller.commands
import main.scala.model.Card
import main.scala.model.Map

import scala.util.Try
trait Command {
  var x:Int
  var y:Int
  def execute(x:Int, y:Int,card: Card,map: Map): Try[Int]
  def undo(card: Card,map: Map): Boolean
}
