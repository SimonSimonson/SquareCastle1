package util

import main.scala.model.Player
import mainn.scala.model.KI

case class playerFactory() {

  def create(s : String, name : String): (Player, KI) = {
    if(s == "Player"){
      return (new Player(name), null)
    } else {
      return (null, new KI())
    }
  }

}