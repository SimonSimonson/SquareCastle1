package controller.states

import controller.{Controller, ControllerInterface}
import main.scala.model.{Card, KI, Map, Player}
import model.{CardInterface, KIInterface, MapInterface, PlayerInterface}


case class PvP() extends State(){

  override def handle(state:Boolean, controller:ControllerInterface, playerA:PlayerInterface, playerB:PlayerInterface, bot:KIInterface, bot2:KIInterface, map:MapInterface, card: CardInterface): Int ={
    if(controller == null)
      return -1

    if(state)
      return controller.Options(card,map,playerA)//kann erstmal nur einen Player
    else
      return controller.Options(card,map,playerB)
  }

}
