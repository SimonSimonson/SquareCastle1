package controller.commands

import main.scala.model.{Card, Map}
import util.Command
import supervisor.supervisor

case class mapCommand() extends Command{
  override def execute(i: String, supervisor:supervisor): Boolean ={
    val array = i.split("x")
    val x = array(0).toInt
    val y = array(1).toInt
    /*
    if(x < 2 || y < 2){
      println(Console.WHITE + "Das Spielfeld muss mindestens 2x2 groß sein!", 0)
      execute(i)
    } else {
      val mapSize = new Map(array(0).toInt, array(1).toInt)
      supervisor.map = mapSize
      return mapSize
    }
     */

    val mapSize = new Map(array(0).toInt, array(1).toInt)
    supervisor.map = mapSize
    true
  }

  override def undo(supervisor:supervisor): Unit ={
    supervisor.map = null
  }
}
