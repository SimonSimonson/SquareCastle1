package supervisor


import controller.{CardChangedEvent, Controller, GameOverEvent, NewRoundEvent}
import main.scala.model.{Card, Map, Player}
import main.scala.util.{Observable, Observer}
import main.scala.model.KI

import scala.swing.Publisher

class supervisor(controller: Controller) extends Publisher{
  var p1:Player = _
  var p2:Player = _
  var bot:KI = _
  var map:Map = _
  var runden = 0
  var botyesno = false
  var card:Card =_
  var playersturn:Player =_
  var state:Boolean =_
  //state wechselt zwischen spieler1 und 2 / spieler1 und bot

  def otherplayer():Unit={
    state = !state
  }


  def newRound(): Int = {
    if (runden <= 0) {
      publish(new GameOverEvent)
      return -1
    }
    runden = runden - 1
    controller.print(map)
    if (state) {
      card = controller.Kartezeigen(p1)
      playersturn = p1
    } else {
      card = controller.Kartezeigen(p2)
      if(p2 == null) {
        controller.state.handle(false,controller,p1 ,p2 ,bot ,map, card)
      }
      playersturn = p2
    }
    publish(new NewRoundEvent)
    publish(new CardChangedEvent)
    return 1
  }
  def newRoundactive(): Int = {
    var i = controller.state.handle(state,controller,p1 ,p2 ,bot ,map, card)
    //publish(new NewRoundEvent)
    println(this.card)
    return i
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
