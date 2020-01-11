package gamecontrol.states

import gamecontrol.controller.ControllerInterface
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

case class PvBot() extends State(){

  override def handle(state:Boolean, controller:ControllerInterface, playerA:PlayerInterface, playerB:PlayerInterface, bot:KIInterface, bot2:KIInterface, map:MapInterface, card: CardInterface): Int ={
    if(controller == null)
      return -1
    if(state) {
      return controller.Options(card, map, playerA) //kann erstmal nur einen Player
    }
    else
      controller.Calculatebot(bot,card,map)
    1
  }
}
