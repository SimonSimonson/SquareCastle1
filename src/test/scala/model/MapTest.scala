
package model

import controller.Controller
import main.scala.model.{Card, Map}
import org.scalatest._

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
      field(1)(0) = card1
      field(0)(1) = card2
      field(1)(2) = card3
      field(2)(1) = card4

      map.pruefen(legen1, 1, 1) should be (true)
      map.pruefen(legen2, 0, 1) should be (false)

    }
    "have tipp" in {
      val Controller = new Controller
      val card = new Card(0,1,2,1)
      val map = new Map(3,3)

      Controller.tipp(card,map) should be (true)

    }


    }}
}
