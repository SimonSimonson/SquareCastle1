package controller


import main.scala.model.{Card, Map, Player}
import mainn.scala.model.KI
import util.State

case class StateB() extends State(){

  override def handle(state:Boolean,controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, card: Card): Unit ={
    if(state)
      controller.Optionen(card,map,playerA)
    else
      controller.Calculatebot(bot,card,map)
  }

  override def state(): Int={
    return 1
  }

}
