package util

import controller.Controller
import main.scala.model.Map
import main.scala.model.Card
import scala.util.{Try, Success, Failure}
import supervisor.supervisor

import scala.collection.mutable

case class Invoker(controller: Controller){

    private var stack: List[Command] = Nil

    def ExecuteCommand(command: Command, x:Int, y:Int, card: Card,map:Map):Try[Int]={
      stack = command::stack
      val problem = command.execute(x,y,card,map)
      problem match {
        case Success(v) => Success(v)
        case Failure(e) => Failure(e)
      }
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
