package controller

import main.scala.model.{Map, Player}
import mainn.scala.model.KI
import util.State


case class StateA() extends State(){

  override def handle(controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, runden:Int): Unit ={
    controller.start(playerA, playerB, map, runden)
  }

  override def state(): Int ={
    return 0
  }

}
