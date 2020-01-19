package scala
import JsonImpl.Json
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import gamecontrol.controller.{Controller, ControllerInterface}
import gamecontrol.supervisor.{SupervisorInterface, supervisor}
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.FileIOComponent.XmlImpl.Xml
import gamemodel.model.CardComponent.Card
import gamemodel.model.KIComponent.KI
import gamemodel.model.MapComponent.Map
import gamemodel.model.PlayerComponent.Player
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}

class GameModule extends AbstractModule{
  override def configure(): Unit = {
    val defaultname = ""
    val defaultnum = 0
    bindConstant().annotatedWith(Names.named("name")).to(defaultname)
    bindConstant().annotatedWith(Names.named("num")).to(defaultnum)

    bind(classOf[PlayerInterface]).to(classOf[Player])
    bind(classOf[KIInterface]).to(classOf[KI])
    bind(classOf[MapInterface]).to(classOf[Map])
    bind(classOf[CardInterface]).to(classOf[Card])
    bind(classOf[ControllerInterface]).to(classOf[Controller])
    bind(classOf[SupervisorInterface]).to(classOf[supervisor])
  }
}
