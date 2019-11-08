package TUI
case class TUI() {

  def start(): Unit = {

    newGame()
    setPlayer()



  }
  def newGame(): Unit ={
    print(Console.RED + " Neues Spiel erstellen...")
    val x = scala.io.StdIn.readLine().toString

    if(x.equals("Ja"))

    null
  }
  def setPlayer(): Unit ={
    print(Console.GREEN + " PLAYER NAME PLAYER 01")
    val player1 = Player(scala.io.StdIn.readLine().toString)
    print(Console.GREEN + " PLAYER NAME PLAYER 02")
    val player2 = Player(scala.io.StdIn.readLine().toString)



  }
}
