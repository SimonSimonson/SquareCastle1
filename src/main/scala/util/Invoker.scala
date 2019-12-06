package util

import controller.Controller
import main.scala.model.Card
import supervisor.supervisor

import scala.collection.mutable

case class Invoker(controller: Controller, supervisor: supervisor){

    private var stack: List[Command] = Nil

    def ExecuteCommand(command: Command, string: String):Boolean={
      stack = command::stack
      return command.execute(string, supervisor)
    }
    def Undo()={
      stack match{
        case Nil =>
        case head::stack1 => {
          head.undo(supervisor)
          stack =stack1
        }
      }
    }
}
