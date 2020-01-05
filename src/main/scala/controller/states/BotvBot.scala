package controller.states

import controller.Controller
import main.scala.model.{Card, KI, Map, Player}

case class BotvBot() extends State(){

  override def handle(state:Boolean, controller:Controller, playerA:Player, playerB:Player, bot:KI, bot2:KI, map:Map, card: Card): Int ={
    if(state) {
      controller.Calculatebot(bot,card,map)    }
    else
      controller.Calculatebot(bot2,card,map)
    1
  }

  override def state(): Int={
    return 1
  }

}