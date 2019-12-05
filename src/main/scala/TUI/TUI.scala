package main.scala.TUI
import main.scala.util.{Observable, Observer}
import controller.{Controller, StateA, StateB}
import main.scala.model.Player
import mainn.scala.model.KI
import main.scala.model.Map
import util.playerFactory
import supervisor.supervisor


case class TUI(controller:Controller, supervisor: supervisor) extends Observer {
  controller.add(this)

  def start(): Int = {
    start(newGame(),botornot(),setMap(),Runden(),setPlayer(1),null)
    1
  }
  //false: Ausgabe, true: Reinschreiben der Antwort
  //es fehlen noch die drehungen, die sich grade unendlich drehen
  def settings(int: Int, mode : Boolean) : Int ={
    int match {

      case 0 => {
        if (!mode)
          prettyprint(Console.RED + "Neues Spiel erstellen ?")
        else if (newGame() == 0) {
          update("SPIEL WIRD BEENDET", 1)
          return -1
        }
        return 1
      }


      case 1 => {
        if(!mode)
          prettyprint(Console.RED + "Gib die Map Größe ein (Bsp.: 2x2)")
        else
          setMap()
        1
      }


      case 2 => {
        if(!mode)
          prettyprint(Console.RED + "Rundenanzahl?")
        else {
          val r = Runden()
          if (r == -1) {
            update("Rundenanzahl falsch", 0)
            return -1
          }
        }
        1
      }

      case 3 => {
        if(!mode){
          prettyprint(Console.RED + "Gib deinen Namen ein Spieler " + 1)
          1
        }else {
          val player1 = setPlayer(1)
          supervisor.p1 = player1
          1
        }
      }
      case 4 => {
        if (!mode) {
          update("Willst du gegen einen Bot spielen?", 0)
          return 1
        }
        else {
          var b = botornot()
          if (b == null) {
            return 300
          } else {
            supervisor.bot = b
            return 302
          }
        }
      }


        //spieler oder bot setzen

      case 5 => {
        if (!mode) {
          prettyprint(Console.RED + "Gib deinen Namen ein Spieler " + 2)
          1
        } else {
          val player2 = setPlayer(2)
          supervisor.p2 = player2
          1
        }
      }
      0
    }
  }


  def setPlayer(spielernr: Int): (Player) = {
    val f = new playerFactory
    val player = f.create("Player", input)

    return (player._1)

  }


  //nur zum testen in 2 Methoden geteilt
  def start(g :Int, b : KI, map:Map, runden: Int, playerA: Player, playerB: Player): Int = {
    val g1 = g
    if(runden == -1){
      update("Rundenanzahl falsch",0)
      return -1
    }
    if(g1 == 0){
      update("Goodbye!",0)
      return -1
    }
    val sM = map
    if(b == null) {
      val player1 = playerA
      supervisor.p1 = player1
      val player2 = setPlayer(2)
      supervisor.p2 = player2
      update(Console.WHITE + "Spiel wird gestartet",1)
      prettyprint(".  .  .  .  .  .  .  .")

      var s = new StateA
      controller.changeState(s)
      //s.handle(controller, player1, player2, b, sM, runden)
      //controller.start(player1, player2, sM ,runden)
      1
    }else {
      val player1 = playerA
      supervisor.p1 = player1
      update(Console.WHITE + "Spiel wird gestartet",1)
      prettyprint(".  .  .  .  .  .  .  .")
      val player2 = null

      var s = new StateB
      controller.changeState(s)
      //s.handle(controller, player1, player2, b, sM, runden)
      //controller.startbot(player1, bot, sM, runden)
      1
    }
  }
  def newGame(): Int= {

    val x = input
    if(x =="")
      return 0
    if (x.equals("Ja")|| x.equals("ja") || x.equals("JA") || x.equals("j") || x.equals("J")) {
      return 1
    }

    1
  }


  def botornot(): KI = {
    val y = input
    if(y==null)
      return null
    if (y.equals("Ja") || y.equals("ja") || y.equals("JA") || y.equals("j") || y.equals("J")) {
      return new KI()
    }
    null
    }


  var x = 0
  var y = 0

  def setMap(): Map = {
    val i = input
    val array = i.split("x")
    x = array(0).toInt
    y = array(1).toInt
    if(x < 2 || y < 2){
      update(Console.WHITE + "Das Spielfeld muss mindestens 2x2 groß sein!", 0)
      setMap()
    } else {
      val mapSize = new Map(array(0).toInt, array(1).toInt)
      supervisor.map = mapSize
      return mapSize
    }
  }



  def Runden(): Int = {
    val anzahl = input
    val anzInt = anzahl.toInt
    if(anzahl==null)
      return -1
    if (anzInt <= (x * y) / 2) {
      for (c <- anzahl) {
        if (!c.isDigit) {
          update(Console.WHITE + "SPIEL WIRD ABGEBROCHEN! ?#!*!?!#", 1)
          return -1
        }
      }
      supervisor.runden = x.toInt * 2
      x.toInt
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

  var input = ""
   def input(string: String): Unit={
     controller.befehl = string
    input = string
  }

  override def update(string: String, i: Int): Unit ={

    if(i == 0)
      println(string)
    if(i == 1)
      printf(string)
  }
}