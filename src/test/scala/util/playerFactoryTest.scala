package util

import main.scala.model.Player
import org.scalatest.{Matchers, WordSpec}

class playerFactoryTest extends WordSpec with Matchers{
  "A Playerfactory" when { "new" should {

    val fac = new playerFactory
    "when create is called" in {
      fac.create("Player", "Peter") should not be (null)
      fac.create("a", "") should not be (null)
      fac.create("Player", "Peter") should be (new Player("Peter"))

    }
  }}
}
