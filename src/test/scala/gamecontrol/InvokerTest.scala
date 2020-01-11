package gamecontrol

import gamecontrol.controller.{Controller, Invoker}
import gamecontrol.controller.commands.layCommand
import gamemodel.model.{Card, Map}
import org.scalatest.{Matchers, WordSpec}

import scala.util.Success

class InvokerTest extends WordSpec with Matchers {

  "Invoker" when {
    "new" should {
      val c = new Controller
      val invoker = new Invoker()
      val card = new Card(0,0,0,0)
      val map = new Map(4,4)
      "undo command" in {
        invoker.Undo(card, map) should be (false)
        invoker.ExecuteCommand(new layCommand, 0, 0, card, map) should be (Success(1))
      }

    }
  }
}