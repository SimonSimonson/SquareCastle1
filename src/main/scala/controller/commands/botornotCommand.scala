package controller.commands

import main.scala.model.Card
import mainn.scala.model.KI
import supervisor.supervisor
import util.Command

case class botornotCommand() extends Command{
  override def execute(i: String, supervisor:supervisor): Unit ={
    if(i==null)
      return null
    if (i.equals("Ja") || i.equals("ja") || i.equals("JA") || i.equals("j") || i.equals("J")) {
      return new KI()
    }
  }

  override def undo(supervisor:supervisor): Unit ={
    supervisor.botyesno = null
  }
}
