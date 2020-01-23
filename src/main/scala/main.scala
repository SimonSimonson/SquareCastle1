package scala
import aview.GUI.startScreen
import aview.TUI.{TUI, TUIInterface}
import com.google.inject.{Guice, Injector}
import gamecontrol.controller.{Controller, ControllerInterface}
import gamecontrol.states.{PvBot, PvP}
import gamecontrol.supervisor.{SupervisorInterface, supervisor}
object main {
  def main(args: Array[String]): Unit = {
    val injecor = Guice.createInjector(new GameModule)
    val Controller:ControllerInterface = injecor.getInstance(classOf[ControllerInterface])
    val supervisor:SupervisorInterface = injecor.getInstance(classOf[SupervisorInterface])
    supervisor.controller = Controller
    val tui:TUIInterface = new TUI(Controller, supervisor)
    val start = new startScreen(supervisor,Controller)

    //////////////////////////////////////////////////INITIALISIERUNG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    var mode = false
    var s = ""
/*

    for(i <- 0 to 4) {
      //println(this.card.side0+this.card.side1+this.card.side2+this.card.side3)

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
        Controller.changeState(new PvP)
      }
      if (er == 302) {
        Controller.changeState(new PvBot)

      }
      mode= !mode
    }
*/

    tui.update(Console.WHITE + "Spiel wird gestartet", 0)
    supervisor.state = true



    ///////////////////////////////////////////////////////SPIEL\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


    //state sagt ob spieler1 oder 2 dran ist bzw spieler1 oder bot
    while(true){

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
  }
}
