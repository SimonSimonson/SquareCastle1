package main.scala.TUI
import main.scala.util.{Observable, Observer}
import controller.Controller
import main.scala.model.Player
import mainn.scala.model.KI
import main.scala.model.Map

case class TUI(controller:Controller) extends Observer {
  controller.add(this)

  def start(): Int = {
    start(newGame(),botornot(),setMap(),setPlayer(1),null, Runden())
    1
  }
  //nur zum testen in 2 Methoden geteilt
  def start(g :Int, b : KI, map:Map, playera: Player,playerb: Player, runden: Int): Int = {
    val g1 = g
    val bot = b
    if(runden == -1){
      update("Rundenanzahl falsch",0)
      return -1
    }
    if(g1 == 0){
      update("Goodbye!",0)
      return -1
    }
    val sM = map
    if(bot==null) {
      val player1 = playera
      val player2 = setPlayer(2)
      update(Console.WHITE + "Spiel wird gestartet",1)
      prettyprint(".  .  .  .  .  .  .  .")
      controller.start(player1, player2, sM ,runden)
      1
    }else {
      val player1 = playera
      update(Console.WHITE + "Spiel wird gestartet",1)
      prettyprint(".  .  .  .  .  .  .  .")
      controller.startbot(player1, bot, sM, runden)
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
    botornot(y)
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

  var x = 0
  var y = 0

  def setMap(): Map = {
    prettyprint(Console.RED + "Gib die Map Größe ein (Bsp.: 2x2)")
    val i = scala.io.StdIn.readLine().toString
    val array = i.split("x")
    x = array(0).toInt
    y = array(1).toInt
    if(x < 2 || y < 2){
      update(Console.WHITE + "Das Spielfeld muss mindestens 2x2 groß sein!", 0)
      setMap()
    } else {
      val mapSize = new Map(array(0).toInt, array(1).toInt)
      return mapSize
    }
  }

  def Runden(x: String): Int ={
      for (c <- x) {
        if (!c.isDigit) {
          update(Console.WHITE + "SPIEL WIRD ABGEBROCHEN! ?#!*!?!#", 1)
          return -1
        }
      }
    x.toInt
  }

  def Runden(): Int = {

    prettyprint(Console.RED + "Rundenanzahl?")
    val anzahl = scala.io.StdIn.readLine().toString
    var anzInt = anzahl.toInt
    if(anzahl==null)
      return -1
    if (anzInt <= (x * y) / 2) {
      Runden(anzahl)
      1
    } else {
      var max = (x * y) / 2
      update(Console.WHITE + "Das Spielfeld ist zu klein, du darfst maximal " + max + " Runden auswählen!", 0)
      Runden()
    }

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