package controller.states

import controller.Controller
import main.scala.model.{Card, KI, Map, Player}

case class StateB() extends State(){

  override def handle(state:Boolean, controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, card: Card): Int ={
    if(state) {
      return controller.Optionen(card, map, playerA) //kann erstmal nur einen Player
    }
    else
      controller.Calculatebot(bot,card,map)
    1
  }

  override def state(): Int={
    return 1
  }

}
