package util

import main.scala.model.{KI, Player}

trait PlayerFactoryInterface {
  def create(s : String, name : String): (Player, KI)
}
