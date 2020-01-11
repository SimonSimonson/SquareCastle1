package gamecontrol.states

import gamecontrol.controller.ControllerInterface
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

trait State {
  def handle(state:Boolean, controller:ControllerInterface, playerA:PlayerInterface, playerB:PlayerInterface, bot:KIInterface, bot2:KIInterface, map:MapInterface, card: CardInterface): Int
}
