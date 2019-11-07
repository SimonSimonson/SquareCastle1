package TUI

object TUI {
  def setPlayer(): Unit ={
    print(Console.GREEN + " PLAYER NAME PLAYER 01")
    val player1 = Player(scala.io.StdIn.readLine().toString)
    print(Console.GREEN + " PLAYER NAME PLAYER 02")
    val player2 = Player(scala.io.StdIn.readLine().toString)



  }
}
