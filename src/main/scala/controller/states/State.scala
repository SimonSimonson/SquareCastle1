package controller.states

import controller.{Controller, ControllerInterface}
import main.scala.model.{Card, KI, Map, Player}
import model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

trait State {
  def handle(state:Boolean, controller:ControllerInterface, playerA:PlayerInterface, playerB:PlayerInterface, bot:KIInterface, bot2:KIInterface, map:MapInterface, card: CardInterface): Int
}
