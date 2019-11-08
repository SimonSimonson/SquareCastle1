package main.scala.TUI

import main.scala.model.Player
import main.scala.model.Game

case class TUI() {

  def start(): Unit = {

    val g1 = newGame()
    if(g1 == null){
      println("Goodbye!")
      return -1
    }

    val players = setPlayer()
    g1.start(players._1, players._2, Runden() )

  }

  def newGame(): Game = {
    prettyprint(Console.RED + "Neues Spiel erstellen ?")

    val x = scala.io.StdIn.readLine().toString

    if (x.equals("Ja")|| x.equals("ja") || x.equals("JA") || x.equals("j")) {
      val game = new Game()
      return game
    }

    return null
  }

  def setPlayer(): (Player, Player) = {
    prettyprint(Console.RED + "Gib deinen Namen ein Spieler 1  ")
    val player1 = Player(scala.io.StdIn.readLine().toString)
    prettyprint(Console.RED + "Gib deinen Namen ein Spieler 2 ")
    val player2 = Player(scala.io.StdIn.readLine().toString)

    return (player1, player2)
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

}