package util

import controller.Controller
import main.scala.model.{Card, Map, Player}
import main.scala.model.KI

trait State {

  def state(): Int
  def handle(state:Boolean, controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, card: Card): Int

}
