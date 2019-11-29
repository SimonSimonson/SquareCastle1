package util

import main.scala.model.Player

case class playerFactory() {

  def create(s : String): Player = {
    return new Player(s)
  }

}