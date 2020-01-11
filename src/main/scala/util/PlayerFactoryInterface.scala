package util

import gamemodel.model.{KI, Player}

trait PlayerFactoryInterface {
  def create(s : String, name : String): (Player, KI)
}
