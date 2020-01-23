package gamecontrol.controller

import gamecontrol.controller.commands.Command
import gamemodel.model.{CardInterface, MapInterface}

import scala.util.{Success, Try}

case class Invoker() {

    private var stack: List[Command] = Nil

    def ExecuteCommand(command: Command, x:Int, y:Int,
                       card: CardInterface, map:MapInterface):Try[Int]={
      stack = command::stack
      val problem = command.execute(x,y,card,map)
      problem match {
        case Success(v) => Success(v)
      }
    }
    def Undo(card: CardInterface,map: MapInterface):Boolean={
      stack match{
        case Nil =>false
        case head::stack1 => {
          head.undo(card,map)
          stack =stack1
          true
        }
      }
    }
}
