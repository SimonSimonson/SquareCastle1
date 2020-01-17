package util

import gamemodel.model.KIComponent.KI
import gamemodel.model.PlayerComponent.Player


case class playerFactory() extends PlayerFactoryInterface {
//BRICHT AB WENN BOT NICHT ANLEGEN KANN
override
  def create(s : String, name : String): (Player, KI) = {
    if(s == "Player"){
      return (new Player(name), null)
    } else {
      return (null, new KI())
    }
  }

}