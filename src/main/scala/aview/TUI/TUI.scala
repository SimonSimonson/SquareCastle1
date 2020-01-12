package aview.TUI

import gamecontrol.controller.{ControllerInterface}

import util.{PlayerFactoryInterface, playerFactory}
import gamecontrol.states.PvBot
import gamecontrol.supervisor.SupervisorInterface
import gamecontrol.updateEvent
import gamemodel.model.{KI, Player}
import gamemodel.model

import scala.swing.Reactor


case class TUI(controller:ControllerInterface, supervisor: SupervisorInterface) extends Reactor {
  //gamecontrol.controller.add(this)
  this.listenTo(controller)
    reactions += {
    case event: updateEvent =>
      if(event.code == 0)
        println(event.word)
      if(event.code == 1)
        print(event.word)
  }
  //false: Ausgabe, true: Reinschreiben der Antwort
  def settings(int: Int, mode : Boolean) : Int ={
    int match {

      case 0 => {
        if (!mode)
          prettyprint(Console.RED + "Neues Spiel erstellen ?")
        else{
          if(newGame()==0) {
            update("SPIEL WIRD BEENDET", 1)
            return -1
          }
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
          supervisor.bot = b
          if (b == null)
            return 300
          else
            return 302
        }
      }
        //spieler oder bot setzen
      case 5 => {
        if (!mode) {
          prettyprint(Console.RED + "Gib deinen Namen ein Spieler " + 2)
        } else {
          val player2 = setPlayer(2)
          supervisor.p2 = player2
        }
        return 1
      }
      0
    }
  }
  def testfall(): Unit ={
    //gamecontrol.controller.supervisor.map = new Map(10,10)
    //gamecontrol.controller.supervisor.p1 = new Player("Peter")
    //gamecontrol.controller.supervisor.p2 = new Player("Kurt")
    controller.changeState(new PvBot)
    supervisor.rounds = 4
  }


  def setPlayer(spielernr: Int): (Player) = {
    val f:PlayerFactoryInterface = new playerFactory
    val player = f.create("Player", input)
    return (player._1)
  }


  def newGame(): Int= {
    val x = input
    if (x.equals("Ja")|| x.equals("ja") || x.equals("JA") || x.equals("j") || x.equals("J")) {
      return 1
    }
    0
  }

  def botornot(): KI = {
    val y = input
    if (y.equals("Ja") || y.equals("ja") || y.equals("JA") || y.equals("j") || y.equals("J")) {
      return new KI()
    }
    null
  }

  var x = 0
  var y = 0

  def setMap(): model.Map = {
    val i = input
    val array = i.split("x")
    x = array(0).toInt
    y = array(1).toInt
    if(x < 2 || y < 2){
      update(Console.WHITE + "Das Spielfeld muss mindestens 2x2 groß sein!", 0)
      setMap()
    } else {
      val mapSize = new model.Map(array(0).toInt, array(1).toInt)
      supervisor.map = mapSize
      return mapSize
    }
  }



  def Runden(): Int = {
    val anzahl = input
    var anzInt = 0
    try{
      anzInt = anzahl.toInt
    }catch{
      case e => return -1
    }

    if (anzInt <= (x * y) / 2) {
      for (c <- anzahl) {
        if (!c.isDigit) {
          update(Console.WHITE + "SPIEL WIRD ABGEBROCHEN! ?#!*!?!#", 1)
          return -1
        }
      }
      supervisor.rounds = anzInt * 2
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

  def update(string: String, i: Int): Unit ={

    if(i == 0)
      println(string)
    if(i == 1)
      printf(string)
  }
}