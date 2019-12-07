package controller.commands

import main.scala.model.Card
import main.scala.model.Map
import supervisor.supervisor
import util.Command

case class layCommand() extends Command{
  override var x: Int = _
  override var y: Int = _
  override def execute(xpos:Int, ypos:Int,card: Card,map: Map): Int ={
    x = xpos
    y = ypos
    return map.Setcard(card,x,y)

  }

  override def undo(card: Card,map: Map): Unit ={
    var deleted = map.field(x)(y)
    map.field(x)(y) = null
    card.cleanall()
    map.cleanaround(x,y)

  }
}
