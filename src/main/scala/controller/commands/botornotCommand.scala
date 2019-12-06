package controller.commands

import main.scala.model.Card
import mainn.scala.model.KI
import supervisor.supervisor
import util.Command

case class botornotCommand() extends Command{
  override def execute(i: String, supervisor:supervisor): Boolean ={
    if(i==null)
      return true
    if (i.equals("Ja") || i.equals("ja") || i.equals("JA") || i.equals("j") || i.equals("J")) {
      supervisor.bot = new KI
      return false
    }
    true
  }

  override def undo(supervisor:supervisor): Unit ={
    supervisor.bot = null
  }
}
