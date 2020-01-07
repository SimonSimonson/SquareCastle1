package controller.states

import controller.Controller
import main.scala.model.{Card, KI, Map, Player}


case class PvP() extends State(){

  override def handle(state:Boolean, controller:Controller, playerA:Player, playerB:Player, bot:KI, bot2:KI, map:Map, card: Card): Int ={
    if(controller == null)
      return -1

    if(state)
      return controller.Options(card,map,playerA)//kann erstmal nur einen Player
    else
      return controller.Options(card,map,playerB)
  }

}
