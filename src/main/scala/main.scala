package main.scala

import controller.Controller
import model.Player
import model.Card
import model.Map

object main {
  def main(args: Array[String]): Unit = {

    val Controller = new Controller
    val tui = new TUI.TUI(Controller)
    tui.start()
    val player = Player("Julian")
    player.getPoints()

    val field = new Map(3,3)
    val legen1 = new Card(0,0,2,1)
    val legen2 = new Card(2,2,0,1)

    field.pruefen(legen1, 1, 1)
    field.pruefen(legen2, 1, 1)
    //Bot testen die methoden
    //Controller.tipp(legen1, field)

  }
}
