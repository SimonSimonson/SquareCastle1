package test.scala.TUI
import main.scala.util.{Observable, Observer}
import controller.Controller
import main.scala.model.Player
import main.scala.model.KI
import main.scala.model.Map
import main.scala.TUI.TUI
import org.scalatest.{Matchers, WordSpec}
import supervisor.supervisor
class TUItest extends WordSpec with Matchers {


  "A TUI" when {
    "new" should {

      val controller = new Controller
      val supervisor = new supervisor(controller)
      val tui = new TUI(controller, supervisor)
      /*
      "when not start" in {
        tui.start(0, new KI , new Map(3,3),-1, new Player("Peter"), new Player("Kurt")) should be (-1)
      }
      "when no Bot" in {
        tui.start(0, null , new Map(3,3),-1, new Player("Peter"), new Player("Kurt")) should be (-1)
      }
       */

      "when newgame" in {
        tui.input("Ja")
        tui.newGame() should be (1)
        tui.input("Nein")
        tui.newGame() should be (0)
      }
      "when botornot" in {
        //val b = new KI
        tui.input("nein")
        tui.botornot() should be (null)
      }
    }
  }
}
