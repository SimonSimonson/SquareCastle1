package gamecontrol.states

import gamecontrol.controller.ControllerInterface
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

case class BotvBot() extends State(){

  override def handle(state:Boolean, controller:ControllerInterface, playerA:PlayerInterface, playerB:PlayerInterface, bot:KIInterface, bot2:KIInterface, map:MapInterface, card: CardInterface): Int ={
    if(controller == null)
      return -1
    if(state)
      controller.Calculatebot(bot,card,map)
    else
      controller.Calculatebot(bot2,card,map)
    1
  }



}