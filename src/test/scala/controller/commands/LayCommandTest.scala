package controller.commands

import main.scala.model.Card
import main.scala.model.Map
import org.scalatest.{Matchers, WordSpec}

import scala.util.Success

class LayCommandTest extends WordSpec with Matchers{
  "A Laycommand" when { "new" should {
    val lay = new layCommand
    "when execute is called" in {
      lay.execute(0, 0, new Card(0,0,0,0), new Map(4,4)) should be (Success(1))
      lay.execute(0, -1, new Card(0,0,0,0), new Map(4,4)) should be (Success(-1))
    }
    "when undo is called" in {
      lay.undo(new Card(0,0,0,0),new Map(4,4)) should be (true)
      lay.undo(new Card(0,0,0,0),null) should be (false)

    }
  }}
}
