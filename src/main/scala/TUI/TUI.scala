package main.scala.TUI
import main.scala.util.{Observable, Observer}
import controller.Controller
import main.scala.model.Player
import mainn.scala.model.KI
import main.scala.model.Map

case class TUI(controller:Controller) extends Observer {
  controller.add(this)

  def start(): Int = {
    start(newGame(),botornot(),setMap(),setPlayer(1),null)
    1
  }
  //nur zum testen in 2 Methoden geteilt
  def start(g :Int, b : KI, map:Map, playera: Player,playerb: Player): Int = {
    val g1 = g
    val bot = b
    if(g1 == 0){
      update("Goodbye!",0)
      return -1
    }
    val sM = map
    if(bot==null) {
      val player1 = playera
      val player2 = setPlayer(2)
      controller.start(player1, player2, sM ,Runden())
      1
    }else {
      val player1 = playera
      controller.startbot(player1, bot, sM, Runden())
      1
    }
  }
  def newGame(): Int = {
    prettyprint(Console.RED + "Neues Spiel erstellen ?")
    val x = scala.io.StdIn.readLine().toString
    if(x =="")
      return 0
    newGame(x)
    1
  }
  def newGame(x: String): Int = {
    if (x.equals("Ja")|| x.equals("ja") || x.equals("JA") || x.equals("j") || x.equals("J")) {
      return 1
    }
    0
  }

  def botornot(): KI = {
    update("Willst du gegen einen Bot spielen?", 0)
    val y = scala.io.StdIn.readLine().toString
    if(y==null)
      return null
    var b = botornot(y)
    return b
    }


  def botornot(y:String): KI = {
    if (y.equals("Ja") || y.equals("ja") || y.equals("JA") || y.equals("j") || y.equals("J")) {
      return new KI()
    }
    null
  }


  def setPlayer(spielernr: Int): (Player) = {
    prettyprint(Console.RED + "Gib deinen Namen ein Spieler " + spielernr)
    val player = Player(scala.io.StdIn.readLine().toString)


    return (player)
  }

  def setMap(): Map = {
    prettyprint(Console.RED + "Gib die Map Größe ein (Bsp.: 2x2)")
    val i = scala.io.StdIn.readLine().toString
    val array = i.split("x")
    val mapSize = new Map(array(0).toInt, array(1).toInt)

    return mapSize
  }

  def Runden(): Int = {
    prettyprint(Console.RED + "Rundenanzahl?")
    val x = scala.io.StdIn.readLine().toString
    for (c <- x) {
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

      update(array(ix), 1)
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