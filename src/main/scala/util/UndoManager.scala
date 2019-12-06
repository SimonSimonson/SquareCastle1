package util

class UndoManager {

  //Nil equals List()
  private var undoStack: List[Command]= Nil

  def executeStep(command: Command) = {
    undoStack = command::undoStack
    command.execute
  }

  def undoStep: Unit ={
    undoStack match{
      case Nil => return
      case head::stack => {
        head.undo
        undoStack=stack
      }
    }

  }

}
