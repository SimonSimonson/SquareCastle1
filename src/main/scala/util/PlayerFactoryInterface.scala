package util

import gamemodel.model.KIComponent.KI
import gamemodel.model.PlayerComponent.Player

trait PlayerFactoryInterface {
  def create(s : String, name : String): (Player, KI)
}
