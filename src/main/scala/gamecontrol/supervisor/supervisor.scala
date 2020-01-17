package gamecontrol.supervisor

import com.google.inject.{Guice, Inject}
import gamecontrol.{BotEvent, CardChangedEvent, GameOverEvent, InsertedEvent, NewRoundEvent, SaveEvent}
import gamecontrol.controller.ControllerInterface
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}


class supervisor @Inject()() extends SupervisorInterface {
  override var controller:ControllerInterface =_
  override var p1:PlayerInterface = _
  override var p2:PlayerInterface = _
  override var bot:KIInterface = _
  override var bot2:KIInterface =_
  override var map:MapInterface = _
  override var rounds = 0
  override var botyesno = false
  override var card:CardInterface =_
  override var playersturn:PlayerInterface =_
  override var state:Boolean =_
  val injector = Guice.createInjector(new GameModule)
  var fileIo = injector.getInstance((classOf[FileIOInterface]))
  //state wechselt zwischen spieler1 und 2 / spieler1 und bot

  override def otherplayer():Boolean={
    state = !state
    state
  }


  override def newRound(): Int = {
    println("ANZAHL RUNDEN " + rounds)
    if (rounds <= 0) {
      publish(new GameOverEvent)
      return -1
    }
    rounds = rounds - 1
    controller.print(map)
    publish(new InsertedEvent)
    if (state) {
      card = controller.showCard(p1)
      playersturn = p1
    } else {
      card = controller.showCard(p2)
      playersturn = p2
    }
    publish(new NewRoundEvent)
    publish(new CardChangedEvent(card))
    if(p2 == null && !state || p1 == null) {
      publish(new BotEvent)
    }
    return 1
  }
  override def newRoundactive(): Int = {
    var i = controller.state.handle(state,controller,p1 ,p2 ,bot, bot2 ,map, card)
    //gamecontrol.controller.print(map)Error downloading org.scala-sbt:main_2.12:1.3.3
    //publish(new NewRoundEvent)
    if(map.isFull()){
      publish(new InsertedEvent)
      publish(new GameOverEvent)
    }
    return i
  }
  override def showPoints(): Boolean ={
    if(p1 == null && p2 == null && bot == null && bot2 == null)
      return false
    controller.printpunkte(p1,p2,bot,bot2)
  }

  override def endgame(): String = {
    var s = ""
    var b = 0
    var name = ""
    var a = 0
    var name2 = ""
    if(p1 != null) {
      a = p1.Points
      name2 = p1.toString()
    }
    else {
      println(bot)
      a = bot2.Punkte
      name2 = bot2.toString()
    }
    if(p2 != null) {
      b = p2.Points
      name = p2.toString()
    }
    else {
      println(bot)
      b = bot.Punkte
      name = bot.toString()
    }
    if(a > b)
      return s + name2+" GEWINNT"
    else if(a < b )
      return s + name + " GEWINNT"
    else{
      return s + "UNENTSCHIEDEN"

    }
  }

  def save(map: MapInterface): Unit = {
    fileIo.save(map)
  }

  def load(): Unit = {
    map = fileIo.load()
    publish(new SaveEvent)
  }


}
