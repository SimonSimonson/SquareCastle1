
package gamemodel.model

import gamemodel.model.CardComponent.Card
import gamemodel.model.MapComponent.Map
import org.scalatest._

import scala.util.Success

class MapTest extends WordSpec with Matchers {

  "A Map" when { "new" should {
      val map = new Map(3,3)
    "have a new field" in {
      val field = Array.ofDim[Card](3,3)
    }
    "have pruefen" in {
      val field = Array.ofDim[Card](3,3)
      val card1 = new Card(0,0,0,1)
      val card2 = new Card(0,0,0,1)
      val card3 = new Card(0,0,0,1)
      val card4 = new Card(0,0,0,1)
      val legen1 = new Card(0,1,0,0)
      val legen2 = new Card(2,2,0,1)
      map.field(1)(0) = card1
      map.field(0)(1) = card2
      map.field(1)(2) = card3
      map.field(2)(1) = card4

      map.check(legen1, 1, 1) should be (true)
      field(1)(1)= null
      map.check(legen2, 1, 1) should be (false)

    }


    "have random Card" in {
      val card = new Card(0,0,0,0)
      map.setRandom(card) should be (true)
      map.setRandom(null) should be (false)
    }
    "when Random Card" in {
      val card = new Card(0,1,2,1)
      map.Setcard(card, 5, 5) should be (Success(-1))

    }
    "when cleanaround " in {
      val map = new Map(3,3)
      map.field(1)(0) = new Card(0,0,0,0)
      map.field(1)(2) = new Card(0,0,0,0)
      map.field(0)(1) = new Card(0,0,0,0)
      map.field(2)(1) = new Card(0,0,0,0)
      map.cleanaround(1,1) should be (true)
    }

    }}
}
