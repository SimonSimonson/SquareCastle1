package main.scala.test.scala

import main.scala.model.Card
import org.scalatest._

class CardTest extends WordSpec with Matchers {

  "A Card" when { "new" should {
    val cards = Card(0,1,2,2)
    "have an Array"  in {
      cards.side3 should be (2)
    }
    "when rotateRight"  in {
      cards.rotateRight() should be (true)
      cards.mysides(3) should be (2)
    }
    "when rotateLeft"  in {
      cards.rotateLeft() should be (true)
      cards.mysides(2) should be (3)
    }
    "when karte passt" in {
      val karte1 = Card(0,2,1,2)
      val karte2 = Card(0,1,2,2)
      karte1.anlegen(1, karte2) should be (true)

    }
    "when karte passt nicht" in {
      val karte1 = Card(0,2,1,2)
      val karte2 = Card(0,1,2,1)
      karte1.anlegen(1, karte2) should be (false)

    }
    "when alone" in {
      val none:Array[Card] = new Array[Card](4)
      val roads:Array[Card] = new Array[Card](4)
      val castle:Array[Card] = new Array[Card](4)
      none.isEmpty
      roads.isEmpty
      castle.isEmpty
      cards.CheckifAlone() should be (false)
    }

  }}

}

