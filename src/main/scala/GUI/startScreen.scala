package GUI

import scala.swing._
import javax.swing.JPanel
import java.io.File
import controller.states.{BotvBot, PvP, PvBot}
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import controller.Controller
import javax.swing.JOptionPane
import javax.swing.JTextField
import supervisor.supervisor
import scala.swing.event.ButtonClicked
import main.scala.model.{KI, Map, Player}
import javax.swing.JCheckBox


class startScreen(supervisor: supervisor, controller: Controller) extends MainFrame {

  title = "Square Castle"
  background = java.awt.Color.WHITE
  preferredSize = new Dimension(1000, 700)
  var startsign = false

  val schriftIMG = ImageIO.read(new File("./src/main/scala/GUI/graphics/SQ.png"))
  //var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](supervisor.map.getmx(), supervisor.map.getmy())

  val schrift = new Panel {
    override def paint(g: Graphics2D): Unit = {
      g.drawImage(schriftIMG, 200, 225, null)
    }
  }

  val castleIMG = ImageIO.read(new File("./src/main/scala/GUI/graphics/SQ2.png"))
  //var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](supervisor.map.getmx(), supervisor.map.getmy())

  val castle = new Panel {
    override def paint(g: Graphics2D): Unit = {
      g.drawImage(castleIMG, 200, 225, null)
    }
  }

  val imgPanel = new GridBagPanel() {
    preferredSize = new Dimension(150, 400)
    background = java.awt.Color.WHITE

    val schriftLabel = new Label("")
    val castleLabel = new Label("")

    val imgPanel = new JPanel()
    imgPanel.setAlignmentX(4)
    imgPanel.setAlignmentY(4)


    schriftLabel.background = java.awt.Color.GRAY.brighter().brighter()
    schriftLabel.preferredSize = new Dimension(800, 151)
    schriftLabel.verticalAlignment = Alignment.Top
    schriftLabel.verticalTextPosition = Alignment.Bottom

    castleLabel.background = java.awt.Color.WHITE
    castleLabel.preferredSize = new Dimension(300, 249)
    castleLabel.verticalAlignment = Alignment.Center
    castleLabel.verticalTextPosition = Alignment.Bottom

    add(schriftLabel, constraints(0, 10))
    add(castleLabel, constraints(0, 11))

    // Quelle : http://otfried.org/scala/index_42.html
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    weightx: Double = 0.0, weighty: Double = 0.0,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {

      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c.anchor = GridBagPanel.Anchor.North
      c

    }
    schriftLabel.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/SQ.png")))
    castleLabel.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/SQ2.png")))
  }

  val startButtons = new GridBagPanel() {
    preferredSize = new Dimension(800, 400)
    background = java.awt.Color.WHITE
    val pvp = new Button("PvP")
    val pvbot = new Button("PvE")

    pvp.background = java.awt.Color.GRAY.brighter().brighter()
    pvp.preferredSize = new Dimension(200, 150)
    pvp.verticalAlignment = Alignment.Center
    pvp.verticalTextPosition = Alignment.Bottom
    pvp.font = new Font("Verdana", 1, 40)

    pvbot.background = java.awt.Color.GRAY.brighter().brighter()
    pvbot.preferredSize = new Dimension(200, 150)
    pvbot.verticalAlignment = Alignment.Center
    pvbot.verticalTextPosition = Alignment.Bottom
    pvbot.font = new Font("Verdana", 1, 40)

    add(pvp, constraints(2, 2))
    add(pvbot, constraints(12, 2))

    listenTo(pvp, pvbot)

    val buttons: List[Button] = List(pvp, pvbot)
    reactions += {
      case ButtonClicked(b) =>

        if (b == pvp) {
          choosePVP
          startScreen.this.visible = false
        }
        if (b == pvbot) {
          choosePVBOT
          startScreen.this.visible = false
        }
    }

    // Quelle : http://otfried.org/scala/index_42.html
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    weightx: Double = 0.0, weighty: Double = 0.0,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {

      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c.anchor = GridBagPanel.Anchor.North
      c
    }
  }

  def choosePVP: Unit = {
    val map = new JTextField
    val rundenAnzahl = new JTextField
    val player1 = new JTextField
    val player2 = new JTextField
    val box = new JCheckBox
    val message = Array(" Set mapsize (Bsp: ZxZ): ", " z", map, " ", " Play until the field is full?" , box," (Or set number of Rounds:)", rundenAnzahl, " ", " Player 1:", player1, "   ", " Player 2:", player2)
    val option: Int = JOptionPane.showConfirmDialog(null, message, "SquareCastle", JOptionPane.OK_CANCEL_OPTION)

    setMap(map.getText(), 1)
    setRound(rundenAnzahl.getText(), 1, box, map.getText())
    setPlayer(player1.getText, player2.getText, 1)
  }

  def choosePVBOT: Unit = {
    val map = new JTextField
    val rundenAnzahl = new JTextField
    val player1 = new JTextField
    val box = new JCheckBox
    val message = Array(" Set mapsize (Bsp: ZxZ): ", " z", map, " ", " Play until the field is full?" , box, " Set number of Rounds:", rundenAnzahl, " ", " Player:", player1)
    val option: Int = JOptionPane.showConfirmDialog(null, message, "SquareCastle", JOptionPane.OK_CANCEL_OPTION)

    setMap(map.getText(), 2)
    setRound(rundenAnzahl.getText, 2, box, map.getText())
    setPlayer(player1.getText, "", 2)
    supervisor.bot = new KI
    supervisor.botyesno = true
  }

  def setMap(x: String, z: Int): Boolean = {
    try {
      if (x.equals("")) {
        supervisor.map = new Map(10, 10)
        return true
      }
      val intX = x.toInt
      supervisor.map = new Map(intX, intX)
      true
    } catch {
      case e =>
        println(x + " ist keine Zahl! Bitte gib eine gültige Mapgrösse ein!")
        if (z == 1) {
          choosePVP
          return false
        } else if (z == 2) {
          choosePVBOT
          return false
        }
        return false
    }
  }

  //var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](supervisor.map.getmx(), supervisor.map.getmy())
  // z ausbauen super hässlich
  def setPlayer(player: String, player2: String, z: Int): Boolean = {
    if (player.equals("") && player2.equals("") && z == 1) {
      supervisor.p1 = new Player("Kurt")
      supervisor.p2 = new Player("Franz")
      return true
    }
    if (player.equals("") && player2.equals("") && z == 2) {
      supervisor.p1 = new Player("Klaus")
      supervisor.bot = new KI()
      return true
    }
  startGame(player, player2, 1)
  return true
  }

  def startGame(player:String, player2:String, z:Int): Boolean ={

    if (z == 1) {
    supervisor.p1 = new Player(player)
    supervisor.p2 = new Player(player2)
    val gui = new GUI(supervisor, controller, check)

    supervisor.newRound()

    gui.updateCard(supervisor.card)
    controller.changeState(new PvP)
    startsign = true
    return true
  } else if (z == 2) {

    if(player  == "bot") {
    supervisor.bot2 = new KI()
    controller.changeState(new BotvBot)
  } else {
    supervisor.p1 = new Player(player)
    controller.changeState(new PvBot)
  }
    supervisor.bot = new KI()

    val gui = new GUI(supervisor, controller,check)

    supervisor.newRound()
    gui.updateCard(supervisor.card)

    startsign = true
    return true
  } else {
    return false
  }

  }

  var check = false

  def setRound(x: String, z: Int, box: JCheckBox, map: String): Boolean = {
    if (box.isSelected){
      check = true
      //rounds auf maximum setzen
      //im supervisor eine methode schreiben die sagt ob voll ist, runden auf maximalen intwert setzen (abfragen ob runden kleiner 0 sind oder spielfeld voll
      supervisor.rounds = 1000//map.toInt * map.toInt
    } else {
      if (x.equals("")) {
        supervisor.rounds = 4
        return true
      }
      try {
        val int = x.toInt
        supervisor.rounds = int * 2
        return true
      } catch {
        case e => println(x + " ist keine Zahl! Bitte gib eine gültige Rundenzahl ein!")
          if (z == 1) {
            choosePVP
            return false
          } else if (z == 2) {
            choosePVBOT
            return false
          }
      }
    }
  true
  }

  menuBar = new MenuBar {
    contents += new Menu("") {
      contents += new Separator()
      contents += new MenuItem(scala.swing.Action("made by Julian and Simon") {
      })
      contents += new Separator()

    }
  }

  contents = new BorderPanel {
    //add(menuBar, BorderPanel.Position.North)
    add(imgPanel, BorderPanel.Position.North)
    add(startButtons, BorderPanel.Position.Center)

  }
  centerOnScreen
  visible = true

}
