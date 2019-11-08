
import org.scalatest._


class CardTest extends WordSpec with Matchers {

  "A Card" when { "new" should {
    var cards = Card(0,1,2,2)
    "have an Array"  in {
      cards.side3 should be (2)
    }
    "when rotate"  in {
      cards.rotate() should be (true)
      cards.mysides(3) should be (2)
    }
    "when karte passt" in {
      val karte1 = new Card(0,2,1,2)
      val karte2 = new Card(0,1,2,2)
      karte1.anlegen(1, karte2) should be (true)

    }
    "when karte passt nicht" in {
      val karte1 = new Card(0,2,1,2)
      val karte2 = new Card(0,1,2,1)
      karte1.anlegen(1, karte2) should be (false)

    }
    "when alone" in {
      val none:Array[Card] = new Array[Card](4)
      val roads:Array[Card] = new Array[Card](4)
      val castle:Array[Card] = new Array[Card](4)
      cards.none should be (Array(null,null,null,null))
      cards.roads should be (Array(null,null,null,null))
      cards.castle should be (Array(null,null,null,null))
      cards.CheckifAlone() should be (true)
    }

  }}

}

