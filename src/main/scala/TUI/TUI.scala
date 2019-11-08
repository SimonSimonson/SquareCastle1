package main.scala.TUI
import main.scala.Game
import main.scala.Player

case class TUI() {

  def start(): Unit = {

    val g1 = newGame()
    val players = setPlayer()
    g1.start(players._1,players._2)

  }
  def newGame(): Game ={
    prettyprint(Console.RED + " Neues Spiel erstellen ?")

    val x = scala.io.StdIn.readLine().toString

    if(x.equals("Ja")) {
      println("Spiel gestartet")
      val game = new Game()
      return game
    }
    return null
  }
  def setPlayer(): (Player,Player) ={
    prettyprint(Console.GREEN + " Gib deinen Namen ein spieler1  ")
    val player1 = Player(scala.io.StdIn.readLine().toString)
    prettyprint(Console.GREEN + " Gibt deinen Namen ein spieler2 ")
    val player2 = Player(scala.io.StdIn.readLine().toString)

    return (player1,player2)
  }
  def kartelegen(): Unit={

  def Runden():Int ={
      prettyprint(Console.RED+ "Wie viele Runden?")
      val runden = scala.io.StdIn.readLine().toInt
      return runden
  }

  def prettyprint(s: String): Unit = {
    val array = s.split("")
    for(ix <- 0 until array.length) {

      print(array(ix))
      Thread.sleep(200)
    }

    println()
  }

}
