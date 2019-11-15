package util

import main.scala.util.{Observable, Observer}

class TestObject extends Observer {
  def update(string: String):Unit = println(string)
}

object Observerpattern {
  val observable = new Observable                 //> observable  : de.htwg.util.Observable = de.htwg.util.Observable@23394894
  val observer1 = new TestObject                  //> observer1  : TestObject = TestObject@630045eb
  val observer2 = new TestObject                  //> observer2  : TestObject = TestObject@26ee7a14
  observable.add(observer1)
  observable.add(observer2)
  observable.notifyObservers("Ping")                  //> Ping
  //| Ping
  observable.remove(observer1)
  observable.notifyObservers("Pong ")                      //> Ping
  observable.remove(observer2)
  observable.notifyObservers("Ping")
}
