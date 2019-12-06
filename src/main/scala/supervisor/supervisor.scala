package supervisor

import controller.Controller
import main.scala.model.{Card, Map, Player}
import main.scala.util.{Observable, Observer}
import mainn.scala.model.KI

class supervisor(controller: Controller) extends Observable{
  var p1:Player = _
  var p2:Player = _
  var bot:KI = _
  var map:Map = _
  var runden = 0
  var botyesno = false
  var card:Card =_

  def newRound(state : Boolean): Int = {
    if (runden <= 0)
      return -1
    runden = runden - 1
    controller.print(map)

    if (state) {
      card = controller.Kartezeigen(p1)
      //controller.state.handle(state,controller,p1,p2,bot,map,card)
    } else {
      card = controller.Kartezeigen(p2)
      //controller.state.handle(state,controller, p1, p2, bot, map, card)
    }
    return 1
  }
  def newRoundactive(state: Boolean): Int = {
    if (state) {
      controller.state.handle(state,controller,p1,p2,bot,map,card)
    } else {
      controller.state.handle(state,controller, p1, p2, bot, map, card)
    }
    return 1
  }


  def endgame(): String = {
    var s = ""
    notifyObservers(p1.Punkte.toString,0)
    s += p1.Punkte.toString + "\n"
    var b = 0
    var name = ""
    if(p2 != null) {
      notifyObservers(p2.Punkte.toString,0)
      s += p2.Punkte.toString + "\n"
      b = p2.Punkte
      name = p2.toString()
    }
    else {
      notifyObservers(bot.Punkte.toString,0)
      s += bot.Punkte.toString + "\n"
      b = bot.Punkte
      name = bot.toString()
    }
    if(p1.Punkte > b)
      return s + "SPIELER "+ p1.toString()+" GEWINNT"
    //notifyObservers(Console.RED + "SPIELER " + p1.toString()+" GEWINNT",0)
    //println(Console.RED + p1.toString()+" GEWINNT")
    else if(p1.Punkte < b )
      return s + "SPIELER " + name + " GEWINNT"
    //println(Console.RED + p2.toString()+" GEWINNT")
    else{
      return s + "UNENTSCHIEDEN"
      //notifyObservers("UNENTSCHIEDEN",0)
      //println(Console.RED + "UNENTSCHIEDEN")

    }
    s
  }
}
