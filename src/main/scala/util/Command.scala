package util

import java.lang.ModuleLayer.Controller

import main.scala.model.Card
import supervisor.supervisor

trait Command {
  def execute(string: String, supervisor : supervisor): Boolean
  def undo(supervisor:supervisor): Unit
}
