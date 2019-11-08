
import org.scalatest._


class CardTest extends WordSpec with Matchers {

  "A Card" when { "new" should {
    var cards = Card(1,2,3,0)
    "have an Array"  in {
      cards.side3 should be (3)
    }
    "when rotate"  in {
      cards.rotate() should be (true)
      cards.side3 should be (2)
    }

  }}

}

