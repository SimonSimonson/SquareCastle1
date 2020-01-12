package main.scala
import aview.GUI.startScreen
import aview.TUI.{TUI, TUIInterface}
import gamecontrol.controller.{Controller, ControllerInterface}
import gamecontrol.supervisor.{SupervisorInterface, supervisor}
object main {
  def main(args: Array[String]): Unit = {
    val Controller:ControllerInterface = new Controller
    val supervisor:SupervisorInterface = new supervisor(Controller)

    val tui:TUIInterface = new TUI(Controller, supervisor)
    //tui.testfall()

    val start = new startScreen(supervisor,Controller)

    //val gui = new aview.GUI.GUI.GUI(gamecontrol.controller.supervisor)

    //////////////////////////////////////////////////INITIALISIERUNG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    var mode = false
    var s = ""

    /*for(i <- 0 to 4) {      println(this.card.side0+this.card.side1+this.card.side2+this.card.side3)

      tui.settings(i, mode)
      mode = !mode
      s = scala.io.StdIn.readLine().toString
      tui.input(s)
      val er = tui.settings(i, mode)
      if (er == 300) {

        tui.settings(i + 1, false)
        s = scala.io.StdIn.readLine().toString
        tui.input(s)
        tui.settings(i + 1, true)
        //stateA = PvP
        Controller.changeState(new StateA)
      }
      if (er == 302) {
        //stateB = PvBot
        Controller.changeState(new StateB)

      }
      mode= !mode
    }
*/
    tui.update(Console.WHITE + "Spiel wird gestartet", 0)
    supervisor.state = true



    ///////////////////////////////////////////////////////SPIEL\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


    //state sagt ob spieler1 oder 2 dran ist bzw spieler1 oder bot
    while(true){
      /*if(gamecontrol.controller.supervisor.newRound(state) == -1){
        tui.update("Spiel beendet",0)
        val print = gamecontrol.controller.supervisor.endgame()
        tui.update(print, 0)
        return
      }*/
      //gamecontrol.controller.supervisor.showPoints()
      s = scala.io.StdIn.readLine().toString
      supervisor.newRound()
      tui.input(s)
      while(supervisor.newRoundactive()==2){
        s = scala.io.StdIn.readLine().toString
        tui.input(s)
      }
      supervisor.state = !supervisor.state
      if(s == "exit")
        return

    }

    //Bot testen die methoden
    //Controller.tipp(legen1, field)
    //Pushen, Commiten, unten neuer Branch, später
    //wenn fertig  alles committen pushen in Branch, dann in lokalen master(checkout sagen)
    //merge into in den lokalen master


  }
}
