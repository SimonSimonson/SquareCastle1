package model.Decorator

class Decorator(val easymode: Easymode) extends Easymode {

  var nextPlayerEasy :Easymode = easymode

  override def addPoints(punkte: Int): Unit = nextPlayerEasy.addPoints(punkte: Int)

  override def getPoints(): Int = nextPlayerEasy.getPoints()

  override def addEasyMode(): Boolean = nextPlayerEasy.addEasyMode()
}
