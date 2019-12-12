package controller

import main.scala.model.{Card, Map, Player}
import mainn.scala.model.KI
import util.State


case class StateA() extends State(){

  override def handle(state:Boolean, controller:ControllerTui, playerA:Player, playerB:Player, bot:KI, map:Map, card: Card): Int ={
    if(state)
      return controller.Optionen(card,map,playerA)//kann erstmal nur einen Player
    else
      return controller.Optionen(card,map,playerB)
  }

  override def state(): Int ={
    return 0
  }

}
