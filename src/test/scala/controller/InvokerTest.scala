package controller

import org.scalatest.{Matchers, WordSpec}

class InvokerTest extends WordSpec with Matchers {

  "Invoker" when { "new" should {
    val c = new Controller
    val invoker = new Invoker(c)
  "undo command" in {


}

}}
}