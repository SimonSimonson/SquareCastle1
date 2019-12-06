package controller.commands

import main.scala.model.Card
import supervisor.supervisor
import util.Command

case class rundenCommand() extends Command {
  override def execute(i: String, supervisor: supervisor): Boolean = {
    val anzInt = i.toInt
    if (i == null)
      return false
    supervisor.runden = anzInt * 2
    true
  }


  override def undo(supervisor:supervisor): Unit ={
    supervisor.runden = 0

  }
}
