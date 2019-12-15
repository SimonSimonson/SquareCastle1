package controller.states

import controller.Controller
import main.scala.model.{Card, KI, Map, Player}


case class StateA() extends State(){

  override def handle(state:Boolean, controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, card: Card): Int ={
    if(state)
      return controller.Optionen(card,map,playerA)//kann erstmal nur einen Player
    else
      return controller.Optionen(card,map,playerB)
  }

  override def state(): Int ={
    return 0
  }

}
