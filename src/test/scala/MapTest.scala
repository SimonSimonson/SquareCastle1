
package main.scala.test.scala

import org.scalatest._
import main.scala.model.{Card, Map}

class MapTest extends WordSpec with Matchers {

  "A Map" when { "new" should {
      val map = new Map(3,3)
    "have a new field" in {
      val field = Array.ofDim[Card](3,3)
    }
    "have a nullprint" in {
      map.nullprint(0) should be (" _________ ")
    }


    }}
}
