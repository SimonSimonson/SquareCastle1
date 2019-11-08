package main.scala.model



import scala.collection.mutable.ListBuffer

case class Player(name: String ) {
    val karten = new ListBuffer[Int]()


    override def toString(): String = name

}
