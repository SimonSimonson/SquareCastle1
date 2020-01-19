
import JsonImpl.Json
import gamecontrol.controller.Controller
import gamecontrol.controller.commands.layCommand
import gamemodel.model.CardComponent.Card
import gamemodel.model.MapComponent.Map
import org.scalatest.{Matchers, WordSpec}

import scala.util.Success

class JsonTest extends WordSpec with Matchers {

  "Json" when {
    "new" should {
      val json = new Json
      "undo command" in {
        json.save(new Map(4,4)) should be (true)
        json.load should not be (null)
        json.fieldToJson(new Map(4,4)) should not be (null)
        json.fieldToJson(null) should be (null)


      }

    }
  }
}