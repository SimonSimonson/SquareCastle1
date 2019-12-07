package util

import controller.Controller
import main.scala.model.Map
import main.scala.model.Card

import supervisor.supervisor

import scala.collection.mutable

case class Invoker(controller: Controller){

    private var stack: List[Command] = Nil

    def ExecuteCommand(command: Command, x:Int, y:Int, card: Card,map:Map):Int={
      stack = command::stack
      return command.execute(x,y,card,map)
    }
    def Undo(card: Card,map: Map)={
      stack match{
        case Nil =>
        case head::stack1 => {
          head.undo(card,map)
          stack =stack1
        }
      }
    }
}
