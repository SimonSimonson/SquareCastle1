package controller.commands

import main.scala.model.Card
import supervisor.supervisor
import util.Command

case class rundenCommand() extends Command{
  override def execute(i: String, supervisor:supervisor): Unit ={
    ???
  }

  override def undo(supervisor:supervisor): Unit ={
    ???
  }
}
