package model

import main.scala.model.Card
import org.scalatest._

class CardTest extends WordSpec with Matchers {

  "A Card" when { "new" should {
    val cards = Card(0,1,2,2)
    val cards2 = Card(0,1,2,2)
    "have an Array"  in {
      cards.side0 should be (0)
      cards.side1 should be (1)
      cards.side2 should be (2)
      cards.side3 should be (2)
    }
    "when rotateRight"  in {
      cards.rotateRight() should be (true)
      cards.mysides(3) should be (2)
    }
    "when rotateLeft"  in {
      cards2.rotateLeft() should be (true)
      cards2.mysides(1) should be (2)
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

  }}


}

