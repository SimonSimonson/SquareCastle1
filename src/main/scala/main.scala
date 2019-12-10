package main.scala
import GUI.GUI
import controller.{Controller, StateA, StateB}
import supervisor.supervisor

object main {
  def main(args: Array[String]): Unit = {

    val Controller = new Controller
    val supervisor = new supervisor(Controller)

    val tui = new TUI.TUI(Controller, supervisor)

    val gui = new GUI()

    //////////////////////////////////////////////////INITIALISIERUNG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    var mode = false
    var s = ""
    /*for(i <- 0 to 4) {
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
    tui.testfall()
    tui.update(Console.WHITE + "Spiel wird gestartet", 1)




    ///////////////////////////////////////////////////////SPIEL\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


    //state sagt ob spieler1 oder 2 dran ist bzw spieler1 oder bot
    var state = false
    while(true){
      state = !state
      if(supervisor.newRound(state) == -1){
        tui.update("Spiel beendet",0)
        val print = supervisor.endgame()
        tui.update(print, 0)
        return
      }
      //supervisor.showPoints()
      s = scala.io.StdIn.readLine().toString
      tui.input(s)
      while(supervisor.newRoundactive(state)==2){
        s = scala.io.StdIn.readLine().toString
        tui.input(s)
      }

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
