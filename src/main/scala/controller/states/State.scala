package controller.states

import controller.Controller
import main.scala.model.{Card, KI, Map, Player}

trait State {

  def state(): Int
  def handle(state:Boolean, controller:Controller, playerA:Player, playerB:Player, bot:KI, bot2:KI, map:Map, card: Card): Int

}
