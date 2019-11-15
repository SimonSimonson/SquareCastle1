package main.scala

import controller.Controller

object main {
  def main(args: Array[String]): Unit = {
    val Controller = new Controller
    val tui = new TUI.TUI(Controller)
    tui.start()



  }
}
