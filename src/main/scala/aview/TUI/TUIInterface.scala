package aview.TUI

import gamemodel.model
import gamemodel.model.{KI, Player}

import scala.swing.Reactor

trait TUIInterface extends Reactor {

  var input:String

  def settings(int: Int, mode: Boolean): Int

  def Runden(): Int

  def update(string: String, i: Int): Unit

  def input(string: String): Unit

}
