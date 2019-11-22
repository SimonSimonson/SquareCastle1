package test.scala.TUI
import main.scala.util.{Observable, Observer}
import controller.Controller
import main.scala.model.Player
import mainn.scala.model.KI
import main.scala.model.Map
import main.scala.TUI.TUI

import org.scalatest.{Matchers, WordSpec}
class TUItest extends WordSpec with Matchers {


  "A TUI" when {
    "new" should {

      val controller = new Controller
      val tui = new TUI(controller)

      "when start" in {
        tui.start(0,new KI,new Map(3,3),new Player("Peter"),new Player("Kurt"),-1) should be (-1)

      }
      "when newgame" in {
        tui.newGame("Ja") should be (1)
        tui.newGame("Nein") should be (0)
      }
      "when botornot" in {
        //val b = new KI
        tui.botornot("nein") should be (null)
      }
    }
  }
}
