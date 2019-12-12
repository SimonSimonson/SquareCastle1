package supervisor

import controller.ControllerTui
import main.scala.model.{Card, Map, Player}
import main.scala.util.{Observable, Observer}
import mainn.scala.model.KI

class supervisor(controller: ControllerTui) extends Observable{
  var p1:Player = _
  var p2:Player = _
  var bot:KI = _
  var map:Map = _
  var runden = 0
  var botyesno = false
  var card:Card =_

  //state wechselt zwischen spieler1 und 2 / spieler1 und bot
  def newRound(state : Boolean): Int = {
    if (runden <= 0)
      return -1
    runden = runden - 1
    controller.print(map)
    if (state) {
      card = controller.Kartezeigen(p1)
    } else {
      card = controller.Kartezeigen(p2)
    }
    return 1
  }
  //wie oben
  def newRoundactive(state: Boolean): Int = {
    if (state) {
      return controller.state.handle(state,controller,p1 ,p2 ,bot ,map, card)
    } else {
      return controller.state.handle(state,controller,p1, p2, bot, map, card)
    }
  }
  def showPoints(): Unit ={
    controller.printpunkte(p1,p2,bot)
  }

  def endgame(): String = {
    var s = ""
    var b = 0
    var name = ""
    if(p2 != null) {
      b = p2.Punkte
      name = p2.toString()
    }
    else {
      println(bot)
      b = bot.Punkte
      name = bot.toString()
    }
    if(p1.Punkte > b)
      return s + "SPIELER "+ p1.toString()+" GEWINNT"
    else if(p1.Punkte < b )
      return s + "SPIELER " + name + " GEWINNT"
    else{
      return s + "UNENTSCHIEDEN"

    }
    s
  }
}
