package util

import scala.util.{Try, Success, Failure}
import main.scala.model.Card
import main.scala.model.Map


trait Command {
  var x:Int
  var y:Int
  def execute(x:Int, y:Int,card: Card,map: Map): Try[Int]
  def undo(card: Card,map: Map): Unit
}
