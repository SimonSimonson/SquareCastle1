package model.Decorator

class PlayerEasy(override val easymode: Easymode)extends Decorator(easymode) {
  override def toString: String = super.toString+ " [Neuling] "
}
