package util

import main.scala.model.Player
import main.scala.model.KI

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