package controller


import main.scala.model.{Map, Player}
import mainn.scala.model.KI
import util.State

case class StateB() extends State(){

  override def handle(controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, runden:Int): Unit ={
    controller.startbot(playerA, bot, map, runden)
  }

  override def state(): Int={
    return 1
  }

}
