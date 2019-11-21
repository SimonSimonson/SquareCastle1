package main.scala.TUI
import main.scala.util.{Observable, Observer}
import controller.Controller
import main.scala.model.Player
import mainn.scala.model.KI

case class TUI(controller:Controller) extends Observer {
  controller.add(this)
  def start(): Unit = {

    val g1 = newGame()
    val bot = botornot()
    if(g1 == 0){
      println("Goodbye!")
      return -1
    }
    if(bot==null) {
      val player1 = setPlayer(1)
      val player2 = setPlayer(2)
      controller.start(player1, player2, Runden())
      return
    }else {
      val player1 = setPlayer(1)
      controller.startbot(player1,bot,Runden())
    }
  }

  def newGame(): Int = {
    prettyprint(Console.RED + "Neues Spiel erstellen ?")

    val x = scala.io.StdIn.readLine().toString

    if (x.equals("Ja")|| x.equals("ja") || x.equals("JA") || x.equals("j") || x.equals("J")) {
      return 1

    }

    0
  }

  def botornot(): KI={
    println("Willst du gegen einen Bot spielen?")

    val y = scala.io.StdIn.readLine().toString

    if (y.equals("Ja")|| y.equals("ja") || y.equals("JA") || y.equals("j") || y.equals("J")) {
      return new KI()
    }
    null
  }

  def setPlayer(spielernr:Int): (Player) = {
    prettyprint(Console.RED + "Gib deinen Namen ein Spieler "+ spielernr)
    val player = Player(scala.io.StdIn.readLine().toString)


    return (player)
  }

    def Runden(): Int = {
      prettyprint(Console.RED + "Rundenanzahl?")
      val x = scala.io.StdIn.readLine().toString
      for(c <- x) {
        if (!c.isDigit) {
          println(Console.WHITE + "SPIEL WIRD ABGEBROCHEN! ?#!*!?!#")
          return -1
        }
      }
      println(Console.WHITE + "Spiel wird gestartet")
      prettyprint(".  .  .  .  .  .  .  .")
      val runden = x.toInt
      println(Console.WHITE + runden)
      return runden
    }

    def prettyprint(s: String): Unit = {
      val array = s.split("")
      for (ix <- 0 until array.length) {

        print(array(ix))
        //Thread.sleep(120)
      }

      println()
    }

  override def update(string: String, i: Int): Unit ={

    if(i == 0)
      println(string)
    if(i == 1)
      printf(string)
  }
}