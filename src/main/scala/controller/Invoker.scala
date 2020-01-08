package controller

import controller.commands.Command
import main.scala.model.{Card, Map}

import scala.util.{Failure, Success, Try}

case class Invoker(controller: Controller){

    private var stack: List[Command] = Nil

    def ExecuteCommand(command: Command, x:Int, y:Int, card: Card,map:Map):Try[Int]={
      stack = command::stack
      val problem = command.execute(x,y,card,map)
      problem match {
        case Success(v) => Success(v)
      }
    }
    def Undo(card: Card,map: Map):Boolean={
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
