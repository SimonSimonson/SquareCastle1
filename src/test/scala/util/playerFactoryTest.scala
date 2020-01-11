package util

import gamemodel.model.Player
import org.scalatest.{Matchers, WordSpec}

class playerFactoryTest extends WordSpec with Matchers{
  "A Playerfactory" when { "new" should {

    val fac = new playerFactory
    "when create is called" in {
      fac.create("a", "")._1 should be (null)
      fac.create("a", "")._2 should not be (null)
      fac.create("Player", "Peter")._2 should be (null)
      fac.create("Player", "Peter")._1 should not be (null)

    }
  }}
}
