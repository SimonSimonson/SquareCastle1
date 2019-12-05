package model.Decorator

class PlayerProfi(override val easymode: Easymode)extends Decorator(easymode) {
  override def toString: String = super.toString+ " [Profi] "
}
