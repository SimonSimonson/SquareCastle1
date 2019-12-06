package model.Decorator

class BasePlayer(override val easymode: Easymode) extends Decorator(easymode){
  var easy = false

  override def toString: String = super.toString + "EasyMode"

  override def addEasyMode(): Boolean = super.addEasyMode()
}
