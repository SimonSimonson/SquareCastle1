package main.scala.test.scala

import main.scala.model.{Card, Player}
import org.scalatest._

import scala.collection.mutable.ListBuffer


class PlayerTest extends WordSpec with Matchers {

  "A player" when { "new" should {
    val player = Player("Julian")
    "have a name"  in {
      player.name should be("Julian")
    }
    "have a nice String representation" in {
      player.toString should be("Julian")
    }
    "get Points" in {
      player.addPoints(2)
      player.getPoints() should be(2)
    }
  }}

}