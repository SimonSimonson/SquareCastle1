package GUI

import java.awt

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._
import java.awt.image.BufferedImage

import javax.swing.{JFrame, JPanel, JScrollPane}
import java.io.File

import javax.imageio.ImageIO
import javax.swing.ImageIcon
import java.awt.Image

import controller.Controller
import javax.swing.JOptionPane
import javax.swing.JPasswordField
import javax.swing.JTextField
import supervisor.supervisor
import java.awt.event.ActionListener

import scala.swing.event.{ButtonClicked, MouseClicked}
import main.scala.model.Map

class startScreen(supervisor: supervisor, controller: Controller) extends MainFrame {

  title = "Square Castle"
  background = java.awt.Color.WHITE
  preferredSize = new Dimension(1000, 700)


  val schriftIMG = ImageIO.read(new File("/Users/julian/Desktop/SE/SquareCastle/src/main/scala/GUI/graphics/SQ.png"))
  //val schriftIMG = ImageIO.read(new File("/home/simon/IdeaProjects/SquareCastle1/src/main/scala/GUI/graphics/SQ.png"))
  //var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](supervisor.map.getmx(), supervisor.map.getmy())

  val schrift = new Panel {
    override def paint(g: Graphics2D): Unit = {
      g.drawImage(schriftIMG, 200, 225, null)
    }
  }

  val castleIMG = ImageIO.read(new File("/Users/julian/Desktop/SE/SquareCastle/src/main/scala/GUI/graphics/SQ2.png"))
  //val castleIMG = ImageIO.read(new File("/home/simon/IdeaProjects/SquareCastle1/src/main/scala/GUI/graphics/SQ2.png"))
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
    val pvbot = new Button("PvBot")

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
          //methode settings
          //controller.status = new StatusA
          choosePVP
          val gui = new GUI(supervisor, controller)
          startScreen.this.visible = false
        }
        if (b == pvbot) {
          //methode settings
          //controller.status = new StatusA
          choosePVBOT
          val gui = new GUI(supervisor, controller)
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

  def choosePVBOT: Unit = {
    val mapX = new JTextField
    val mapY = new JTextField
    val rundenAnzahl = new JTextField
    val player1 = new JTextField
    val message = Array(" Set mapsize (Bsp: 2x5): ", " x", mapX, " y", mapY, " "," Set number of Rounds:", rundenAnzahl, " ", " Player:", player1)
    val option: Int = JOptionPane.showConfirmDialog(null, message, "SquareCastle", JOptionPane.OK_CANCEL_OPTION)
  }

  def choosePVP: Unit = {
    val mapX = new JTextField
    val mapY = new JTextField
    val rundenAnzahl = new JTextField
    val player1 = new JTextField
    val player2 = new JTextField

    val message = Array(" Set mapsize (Bsp: X*Y): ", " x", mapX, " y", mapY, " ", " Set number of Rounds:", rundenAnzahl, " ", " Player 1:", player1, "   ", " Player 2:", player2)
    val option: Int = JOptionPane.showConfirmDialog(null, message, "SquareCastle", JOptionPane.OK_CANCEL_OPTION)

    val intX = mapX.getText().toInt
    val intY = mapY.getText().toInt
    setMap(intX, intY)
    //supervisor.map = new Map(intX, intY)
  }



  def setMap(x:Int, y:Int): Boolean = {
    supervisor.map = new Map(x, y)
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
