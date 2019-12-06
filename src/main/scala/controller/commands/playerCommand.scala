package controller.commands

import main.scala.model.Card
import supervisor.supervisor
import util.{Command, playerFactory}

case class playerCommand(int:Int) extends Command{

  override def execute(i: String, supervisor:supervisor): Boolean ={
    val f = new playerFactory
    val player = f.create("Player", i)
    if(int == 1){
      supervisor.p1 = player._1
      true
    } else if(int == 2){

      supervisor.p2 = player._1
      true
    }
    true
  }

  override def undo(supervisor:supervisor): Unit ={
    supervisor.p1 = null
    supervisor.p2 = null
  }
}
