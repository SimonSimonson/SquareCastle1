package gamecontrol.controller

import java.awt.geom.AffineTransform
import java.awt.image.{AffineTransformOp, BufferedImage}

import gamecontrol.{CardChangedEvent, DoesntFitEvent, InsertedEvent, PlayerEvent, TippEvent, WaitEvent, updateEvent}
import gamecontrol.controller.commands.layCommand
import gamecontrol.states.State
import gamemodel.model.{CardInterface, KIInterface, MapInterface, PlayerInterface}
import com.google.inject.{Guice, Inject}
import gamemodel.FileIOComponent.FileIOInterface

class Controller @Inject() extends ControllerInterface {
  val injector = Guice.createInjector(new GameModule)
  override var state:State = _
  override var befehl:String = _
  override val invoker:Invoker = new Invoker()
  var fileIo = injector.getInstance((classOf[FileIOInterface]))

  override def changeState(state: State):Boolean={
    if(state== null)
      return false
    this.state = state
    true
  }


  override def Calculatebot(bot: KIInterface, card: CardInterface, map:MapInterface):Boolean ={
    if(bot == null)
      return false
    val data = bot.anlegen(map, card)
    for (i <- 0 until data._3) {
      card.rotateRight()
    }
    //irgendwas mit der if abfrage ist am sack
    if (data._1 < 0 || data._2 < 0 || data._3 < 0) {
      map.setRandom(card)
      //notifyObservers("beep boop ich kann nicht anlegen", 0);
      return true
    }
    publish(new updateEvent("Bot legt auf " + data._1 + " " + data._2, 0))
    //card.cleanall()
    map.Setcard(card, data._1, data._2)
    println("Player "+ bot + " bekommt " + card.getAngelegte()+ "  Punkte")

    bot.addPoints(card.getAngelegte())
    //publish(new InsertedEvent)
    //publish(new BotEvent)
    true
  }

  def RandomCard(): CardInterface= {
    val r = scala.util.Random
    val s1 = r.nextInt(3)
    val s2 = r.nextInt(3)
    val s3 = r.nextInt(3)
    val s4 = r.nextInt(3)
    val card = injector.getInstance(classOf[CardInterface])
    card.mysides(0) = s1
    card.mysides(1) = s2
    card.mysides(2) = s3
    card.mysides(3) = s4
    return card
  }
  override def showCard(player:PlayerInterface): CardInterface ={
    val card = RandomCard()
    if(showCard(player,card)) {
      //publish(new NewRoundEvent)
      return card
    }
    else
      return null
  }
  def showCard(player: PlayerInterface, card: CardInterface): Boolean ={
    if(card == null)
      return false
    if(player == null) {
      //publish(new BotEvent)
      publish(new updateEvent(Console.RED + "BOT ist an der Reihe",0))
      true
    } else {
      publish(new PlayerEvent)
      publish(new updateEvent(Console.RED + "Spieler " + player.toString() + " ist an der Reihe", 0))
    }
    publish(new updateEvent(Console.WHITE,0))
    printcard(card)
    if(player != null)
      publish(new updateEvent(Console.BLUE + "r.... rechts rum rotieren"+ Console.CYAN + "  l.... links rum rotieren"+
        Console.MAGENTA + "  i x y.... Einfügen bei (x,y)"+ Console.YELLOW + "  wait.... eine runde aussetzen"+
        Console.BLACK + "  tipp .... zeigt wo man anlegen kann"+ Console.RED + "  exit .... beenden",0))
    true

      //notifyObservers(Console.RED + "Spieler " + player.toString() + " ist an der Reihe",0)
    //notifyObservers(Console.WHITE,0)
  }

//return 1: normaler fall, return -1: fehler fall,  return 2 : spieler darf nocheinmal
  override def Options(card: CardInterface, map: MapInterface, player: PlayerInterface): Int = {

      //auslagern durch neuen parameter der String
      val x = befehl
      val array = x.split(" +")

      if (array(0).equals("r")) {
        card.rotateRight()
        printcard(card)
        publish(new CardChangedEvent(card))
        return 2
      } else if (array(0).equals("wait")) {
        //muss implementiert werden
        publish(new WaitEvent)
        return 2

      } else if (array(0).equals("l")) {
        card.rotateLeft()
        printcard(card)
        publish(new CardChangedEvent(card))
        return 2

      } else if (array(0).equals("i")) {
        if (invoker.ExecuteCommand(new layCommand,array(1).toInt,array(2).toInt,card,map).get == 1) {
          val punkte = card.getAngelegte()
          player.addPoints(punkte)
          publish(new InsertedEvent)
          publish(new updateEvent("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte",0))
          //println("Spieler "+ player.toString()+ " erhält "+ punkte + " Punkte")
          //println(player.toString() +"  :"+ player.Punkte)
          return 1
        } else {
          publish(new DoesntFitEvent)
          publish(new updateEvent("Die Karte passt nicht",0))
          //println("Die Karte passt nicht")
          return 2
        }
      } else if (array(0).equals("undo")) {
        invoker.Undo(card,map)
        print(map)
        return 2
      } else if (array(0).equals("tipp")) {
        publish(new TippEvent)
        //tipp(card, map)
        return 2
      }

    publish(new updateEvent("BEFEHL NICHT ERKANNT",0))
    2
  }

  override def getPoints(player1: PlayerInterface, player2: PlayerInterface): (Int,Int) ={
    val points1 = player1.getPoints()
    //println(points1)
    val points2 = player2.getPoints()
    //println(points2)
    return (points1,points2)
  }

  override def tipp(card: CardInterface, map: MapInterface): Array[Array[Int]] = {
    if(map == null || card == null)
      return null
    val ar:Array[Array[Int]] = Array.ofDim[Int](map.getmx(),map.getmy())
    println(Console.WHITE)
    for (iy <- 0 until map.getmy()) {
      for (zeile <- 0 to 5) {
        for (ix <- 0 until map.getmx()) {
          if(map.field(ix)(iy) != null) {
            ar(ix)(iy)= 0
            printline(zeile,card)
          } else{
            if(map.check(card,ix,iy)) {
              highlight(zeile)
              ar(ix)(iy) = 1
            } else {
              nullprint(zeile)
              ar(ix)(iy) = 0
            }

          }
        }
        publish(new updateEvent("",0))
     }

    }
    ar
  }

  def printline(zeile: Int, card: CardInterface): Boolean={
    if(zeile < 0 || card == null)
      return false
    zeile match{
      case 0 => publish(new updateEvent(" _________ ",1))
      case 1 => publish(new updateEvent("|         |",1))
      case 2 => publish(new updateEvent("|    " + card.mysides(0) + "    |",1))
      case 3 => publish(new updateEvent("| "+card.mysides(3)+"     "+ card.mysides(1)+" |",1))
      case 4 => publish(new updateEvent("|    " + card.mysides(2) + "    |",1))
      case 5 => publish(new updateEvent("|_________|",1))

    }
    true
  }

  def printcard(card: CardInterface): Boolean ={
    if(card == null)
      return false
    publish(new updateEvent(" _________", 0))
    publish(new updateEvent("|         |",0))
    publish(new updateEvent("|    " + card.mysides(0) + "    |",0) )
    publish(new updateEvent("| "+card.mysides(3)+"     "+ card.mysides(1)+" |",0))
    publish(new updateEvent("|    " + card.mysides(2) + "    |",0))
    publish(new updateEvent("|_________|",0))

    true
  }
  def nullprint(zeile: Int): Boolean = {
    if(zeile < 0)
      return false
    zeile match {
      case 0 => publish(new updateEvent(" _________ ",1))
      case 1 => publish(new updateEvent("|         |",1))
      case 2 => publish(new updateEvent("|         |",1))
      case 3 => publish(new updateEvent("|         |",1))
      case 4 => publish(new updateEvent("|         |",1))
      case 5 => publish(new updateEvent("|_________|",1))

    }
    true
  }

  def highlight(zeile: Int): Boolean = {
    if(zeile < 0)
      return false
    zeile match {
      case 0 => publish(new updateEvent(Console.YELLOW + " _________ ",1))
      case 1 => publish(new updateEvent(Console.YELLOW + "|         |",1))
      case 2 => publish(new updateEvent(Console.YELLOW + "|         |",1))
      case 3 => publish(new updateEvent(Console.YELLOW + "|         |",1))
      case 4 => publish(new updateEvent(Console.YELLOW + "|         |",1))
      case 5 => publish(new updateEvent(Console.YELLOW + "|_________|",1))
    }
    printf(Console.WHITE)
    true
  }

  override def print(map: MapInterface): Boolean ={
    if(map == null)
      return false
    publish(new updateEvent(Console.WHITE,0))
    for(iy <- 0 until map.getmy()){
      for(zeile <- 0 to 5){
        for(ix <- 0 until map.getmx()){

          if(map.field(ix)(iy)==null)
            nullprint(zeile)
          else
            printline(zeile,map.field(ix)(iy))
        }
        publish(new updateEvent("",0))
      }

    }
    true
  }
  override def printpunkte(p1:PlayerInterface, p2:PlayerInterface, bot:KIInterface, bot2:KIInterface): Boolean ={
    if(p1 != null)
      publish(new updateEvent(Console.RED+"Punkte von Spieler " + p1.toString() +":  "+ p1.Points,0 ))
    else
      publish(new updateEvent(Console.RED+"Punkte von BOT1 " + bot2.toString() +":  "+ bot2.Punkte,0 ))
    if(p2 != null)
      publish(new updateEvent(Console.RED+"Punkte von Spieler " + p2.toString() +":  "+ p2.Points,0 ))
    else
      publish(new updateEvent(Console.RED+"Punkte von BOT2 " + bot.toString() +":  "+ bot.Punkte,0 ))
    true
  }
  def rotatePic(image:BufferedImage): BufferedImage={
    if(image == null)
      return null
    ///println("DREHE BILD")
    val transform = new AffineTransform
    transform.rotate(1.5708, image.getWidth / 2, image.getHeight / 2)
    val op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
    return op.filter(image, null)
  }

  override def rotatePic(rot: Int, image: BufferedImage): BufferedImage = {
    if(image == null)
      return null
    var tmp: BufferedImage = null
    tmp = image
    var count = rot
    var x = rot
    if(rot < 0) {
      x = (rot * -1) % 4
      x = 4-x
    }
    count = x
    for (i <- 0 until count)
      tmp = rotatePic(tmp)
    return tmp
  }



}
