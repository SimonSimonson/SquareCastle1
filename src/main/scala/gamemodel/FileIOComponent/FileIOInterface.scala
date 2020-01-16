package gamemodel.FileIOComponent

import gamecontrol.supervisor.SupervisorInterface


trait FileIOInterface {

  def load: ()

  def save(supervisor: SupervisorInterface): Unit

}
