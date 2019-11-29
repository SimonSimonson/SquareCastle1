package util

import controller.Controller
import main.scala.model.{Map, Player}
import mainn.scala.model.KI

trait State {

  def state(): Int
  def handle(controller:Controller, playerA:Player, playerB:Player, bot:KI, map:Map, runden:Int): Unit

}
