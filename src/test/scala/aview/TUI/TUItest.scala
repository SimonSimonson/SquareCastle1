package aview.TUI

import gamecontrol.controller.Controller
import org.scalatest.{Matchers, WordSpec}
import gamecontrol.supervisor.supervisor
class TUItest extends WordSpec with Matchers {


  "A aview.TUI" when {
    "new" should {

      val controller = new Controller
      val supervisor = new supervisor()
      supervisor.controller = controller
      val tui = new TUI(controller, supervisor)
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
      "when settings is called " in {
        tui.settings(0, false) should be (1)
        tui.settings(0, true) should be (-1)

        tui.settings(1, false) should be (1)
        tui.input("4x4")
        tui.settings(1, true) should be (1)

        tui.settings(2, false) should be (1)
        tui.input("4")
        tui.settings(2, true) should be (1)
        tui.input("a")
        tui.settings(2, true) should be (-1)

        tui.settings(3, false) should be (1)
        tui.settings(3, true) should be (1)

        tui.settings(4, false) should be (1)
        tui.input("Ja")
        tui.settings(4, true) should be (302)
        tui.input("Nein")
        tui.settings(4, true) should be (300)

        tui.settings(5, false) should be (1)
        tui.settings(5, true) should be (1)

        tui.settings(6, false) should be (0)
      }
      "when SetMap" in{
        tui.input = "1x1"
        tui.setMap() should be (null)
      }
      "when Runden" in {
        tui.input = "a"
        tui.Runden() should be (-1)
        tui.input = "3"
        tui.Runden() should be (6)

      }
    }
  }
}
