package supervisor


import controller.{BotEvent, CardChangedEvent, Controller, GameOverEvent, InsertedEvent, NewRoundEvent}
import main.scala.model.{Card, Map, Player}
import main.scala.util.{Observable, Observer}
import main.scala.model.KI

import scala.swing.Publisher

class supervisor(controller: Controller) extends Publisher{
  var p1:Player = _
  var p2:Player = _
  var bot:KI = _
  var bot2:KI =_
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
    println("ANZAHL RUNDEN " + runden)
    if (runden <= 1) {
      publish(new GameOverEvent)
      return -1
    }
    runden = runden - 1
    controller.print(map)
    publish(new InsertedEvent)
    if (state) {
      card = controller.Kartezeigen(p1)
      playersturn = p1
    } else {
      card = controller.Kartezeigen(p2)
      playersturn = p2
    }
    publish(new NewRoundEvent)
    publish(new CardChangedEvent(card))
    if(p2 == null && !state || p1 == null) {
      publish(new BotEvent) //man muss nur nach dem spielerzug einmal Newround sagen, dann läuft botzug automatisch ab
    }
    return 1
  }
  def newRoundactive(): Int = {
    var i = controller.state.handle(state,controller,p1 ,p2 ,bot, bot2 ,map, card)
    //controller.print(map)
    //publish(new NewRoundEvent)
    //println(this.card)
    return i
  }
  def showPoints(): Unit ={
    controller.printpunkte(p1,p2,bot,bot2)
  }

  def endgame(): String = {
    var s = ""
    var b = 0
    var name = ""
    var a = 0
    var name2 = ""
    if(p1 != null) {
      a = p1.Punkte
      name2 = p1.toString()
    }
    else {
      println(bot)
      a = bot2.Punkte
      name2 = bot2.toString()
    }
    if(p2 != null) {
      b = p2.Punkte
      name = p2.toString()
    }
    else {
      println(bot)
      b = bot.Punkte
      name = bot.toString()
    }
    if(a > b)
      return s + "SPIELER "+ name2+" GEWINNT"
    else if(a < b )
      return s + "SPIELER " + name + " GEWINNT"
    else{
      return s + "UNENTSCHIEDEN"

    }
    s
  }

}
