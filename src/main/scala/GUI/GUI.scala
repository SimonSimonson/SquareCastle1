package GUI

import scala.swing._
import javax.swing.{JFrame, JPanel, JScrollPane}
import java.io.File

import javax.imageio.ImageIO
import javax.swing.ImageIcon
import java.awt.Image

import controller.{CardChangedEvent, Controller, DoesntFitEvent, GameOverEvent, InsertedEvent, NewRoundEvent, TippEvent, WaitEvent}
import javax.swing.JOptionPane
import supervisor.supervisor
import main.scala.model.{Card, Map, Player}

import scala.swing.event.ButtonClicked


class GUI(supervisor:supervisor, controller: Controller) extends MainFrame {

  title = "Square Castle"
  background = java.awt.Color.WHITE
  preferredSize = new Dimension(1000, 700)
  var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](supervisor.map.getmx(), supervisor.map.getmy())
  /*val panel = new Panel {
    override def paint(g: Graphics2D): Unit = {
      g.drawImage(castleIMG, 200, 225, null)
    }
  }*/
  listenTo(controller)
  listenTo(supervisor)

  val actionPanel = new GridBagPanel() {
    preferredSize = new Dimension(110, 400)
    background = java.awt.Color.WHITE

    val header = new Label("Actions")

    val rotateRight = new Button("")
    val rotateLeft = new Button("")
    val tipp = new Button("")
    val pause = new Button("")

    rotateRight.background = java.awt.Color.GRAY.brighter().brighter()
    rotateRight.preferredSize = new Dimension(75, 90)
    rotateRight.verticalAlignment = Alignment.Center
    rotateRight.verticalTextPosition = Alignment.Bottom

    rotateLeft.background = java.awt.Color.WHITE
    rotateLeft.preferredSize = new Dimension(75, 90)
    rotateLeft.verticalAlignment = Alignment.Center
    rotateLeft.verticalTextPosition = Alignment.Bottom

    tipp.background = java.awt.Color.WHITE
    tipp.preferredSize = new Dimension(75, 90)
    tipp.verticalAlignment = Alignment.Center
    tipp.verticalTextPosition = Alignment.Bottom

    pause.background = java.awt.Color.WHITE
    pause.preferredSize = new Dimension(75, 90)
    pause.verticalAlignment = Alignment.Center
    pause.verticalTextPosition = Alignment.Bottom

    val rotateRight_l = new Label("")
    val rotateLeft_l = new Label("")
    val tipp_l = new Label("")
    val pause_l = new Label("")

    header.font = new Font("Verdana", 1, 20)

    rotateRight_l.verticalAlignment = Alignment.Top

    add(header, constraints(0, 0, 1, 1, 0, 0.1))

    add(rotateRight, constraints(0, 1))
    add(rotateRight_l, constraints(0, 2, 1, 1, 0, 0.1))

    add(rotateLeft, constraints(0, 4))
    add(rotateLeft_l, constraints(0, 5, 1, 1, 0, 0.1))

    add(tipp, constraints(0, 7))
    add(tipp_l, constraints(0, 8, 1, 1, 0, 0.1))

    add(pause, constraints(0, 10))
    add(pause_l, constraints(0, 11, 1, 1, 0, 0.1))

    rotateRight.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/rechts.png")))
    rotateLeft.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/links.png")))
    tipp.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/tipp.png")))
    pause.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/pause.png")))

    /*
    rotateRight.text = "<html><body>R" + "<br> I" + "<br>G" + "<br>H" + "<br>T" + "</body></html>\""
    rotateLeft.text = "turn left"
    tipp.text = "hint"
    pause.text = "wait"
     */

    listenTo(rotateRight, rotateLeft, tipp, pause)
    val buttons: List[Button] = List(rotateRight, rotateLeft, tipp, pause)
    reactions += {
      case ButtonClicked(b) =>
          if(b == rotateRight){
            controller.befehl = "r"
            supervisor.newRoundactive()
          }
          if(b == rotateLeft){
            controller.befehl = "l"
            supervisor.newRoundactive()
          }
          if(b == tipp){
            controller.befehl = "tipp"
            supervisor.newRoundactive()
          }
          if(b == pause){
            controller.befehl = "wait"
            supervisor.newRoundactive()
          }
        draw()
        }



    //code

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

  var statusPanel = new GridPanel(1, 1) {

    var n = new JFrame()
    n.setSize(new Dimension(30, 30))

    //var statusline = new TextField(controller.gameStatus.toString, 1)
    var nameField = new TextField("Name")
    nameField.preferredSize = new Dimension(45, 30)
    nameField.font = new Font("Verdana", 1, 35)
    //nameField.columns = 45
    nameField.horizontalAlignment = Alignment.Center
    nameField.editable = false
    nameField.background = java.awt.Color.WHITE


    //var credits = new TextField(controller.players("Punkte: " + controller.gameStatus.id).credits.toString, 1)
    var pointField = new TextField("Punkte: ")
    //pointField.preferredSize = new Dimension(45, 30)
    pointField.font = new Font("Dialog", 1, 35)
    //pointField.columns = 45
    pointField.foreground = java.awt.Color.GREEN.darker().darker()
    pointField.editable = false
    pointField.horizontalAlignment = Alignment.Center
    pointField.background = java.awt.Color.WHITE


    var cardField = new TextField("CARD")
    //cardField.preferredSize = new Dimension(45, 30)
    cardField.font = new Font("Verdana", 1, 35)
    //cardField.columns = 45
    cardField.horizontalAlignment = Alignment.Center
    cardField.background = java.awt.Color.WHITE
    cardField.maximumSize

    contents += nameField
    contents += pointField
    contents += cardField

  }




  val rightPanel = new GridBagPanel() {
    preferredSize = new Dimension(150, 400)
    background = java.awt.Color.WHITE

    val nameLabel = new Label("Player Name")
    val pointsName = new Label("Points:")
    val points = new Label("88")
    val cardLabel =  new Label()

    val imgPanel = new JPanel()
    imgPanel.setAlignmentX(4)
    imgPanel.setAlignmentY(4)


    nameLabel.background = java.awt.Color.GRAY.brighter().brighter()
    nameLabel.preferredSize = new Dimension(100, 90)
    nameLabel.verticalAlignment = Alignment.Center
    nameLabel.verticalTextPosition = Alignment.Bottom

    pointsName.background = java.awt.Color.WHITE
    //pointsName.preferredSize = new Dimension(100, 90)
    pointsName.verticalAlignment = Alignment.Center
    pointsName.verticalTextPosition = Alignment.Bottom

    points.background = java.awt.Color.WHITE
    points.preferredSize = new Dimension(100, 90)
    points.verticalAlignment = Alignment.Center
    points.verticalTextPosition = Alignment.Bottom



    cardLabel.background = java.awt.Color.WHITE
    cardLabel.preferredSize = new Dimension(100, 90)

    var guicell = new GuiCell(0,0, supervisor,controller)
    guicell.setCellPicture
    cardLabel.icon = new ImageIcon(guicell.myPicture)

    nameLabel.verticalAlignment = Alignment.Top
    nameLabel.font = new Font("Verdana", 1, 20)
    pointsName.font = new Font("Verdana", 1, 20)
    points.font = new Font("Verdana", 1, 15)
    cardLabel.font = new Font("Verdana", 1, 20)


    add(nameLabel, constraints(0, 2, 1, 1, 0, 0.1))

    add(points, constraints(0, 5, 1, 1, 0, 0.1))

    add(pointsName, constraints(0, 5, 1, 1, 0, 0.1))

    add(cardLabel, constraints(0, 8, 1, 1, 0, 0.1))


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
//NACH ENDE DER RUNDENZAHL GAMEENDEVENT
//UNTEN DAS BILD ANPASSEN

  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(scala.swing.Action("New Game") {
        val start = new startScreen(supervisor, controller)
        GUI.this.visible = false
      })

      contents += new MenuItem(scala.swing.Action("Change Playernames") {
        var input = JOptionPane.showInputDialog(
          null,
          "Player One",
          "Change Names",
          JOptionPane.QUESTION_MESSAGE
        )
        input = JOptionPane.showInputDialog(
          null,
          "Player Two",
          "Change Names",
          JOptionPane.QUESTION_MESSAGE
        )
      })
      contents += new Separator()
      contents += new MenuItem(scala.swing.Action("Exit") {
        System.exit(0)
      })
    }
  }

  contents = new BorderPanel {

    add(menuBar, BorderPanel.Position.North)
    //add(statusPanel, BorderPanel.Position.East)
    add(actionPanel, BorderPanel.Position.West)
    add(grid, BorderPanel.Position.Center)
    add(rightPanel, BorderPanel.Position.East)

  }
  centerOnScreen
  visible = true

  def grid = new GridPanel(cells.length, cells.length) {
    //border = LineBorder(java.awt.Color.GREEN.darker(), 3)
    background = java.awt.Color.BLACK
    // var grid =
    for {
      line <- cells.indices
      row <- cells.indices
    } {
      var guicell = GuiCell(row, line, supervisor, controller)
      //KLAPPT ALLES BIS DATO
      cells(line)(row) = guicell
      listenTo(guicell)
      contents += guicell
      //println(guicell)
      //speichert daten nicht in array warum auch immer
    }
  }
  draw()
  visible = true


  def draw(): Unit = {
    for {
      line <- cells.indices
      row <- cells.indices
    } cells(line)(row).redrawCell
    repaint
  }
  def updateCard(): Unit ={
    var guicell = new GuiCell(0,0, supervisor,controller)
    guicell.setCellPicture
    rightPanel.cardLabel.icon = new ImageIcon(guicell.myPicture)
    println("Karte aktualisieren")
    rightPanel.repaint()
  }
  def doesntFit(): Unit= {
    JOptionPane.showMessageDialog(
      null,
      "The Card doesnt Fit",
      "",
      JOptionPane.INFORMATION_MESSAGE
    )
  }
  def highlightCell(x:Int,y:Int): Unit ={
    cells(x)(y).background = java.awt.Color.YELLOW
  }


  reactions += {
    case event: NewRoundEvent => //ALLES NEU MALEN(neue spieler gesetzt, neue Karte
      println("NewRoundEvent")
      draw()
    case event: InsertedEvent =>
      println("InsertedEvent")
      draw()
    case event: DoesntFitEvent =>
      println("PasstNichtEvent")
      doesntFit()
    case event: CardChangedEvent =>
      println("CardChangedEvent")
      updateCard()
    case event: GameOverEvent =>
      println("GameOver")
      JOptionPane.showMessageDialog(
        null,
        supervisor.endgame(),
        "Game Over",
        JOptionPane.INFORMATION_MESSAGE
      )
      draw()
      System.exit(1)

    case event: TippEvent =>
      val nums = controller.tipp(supervisor.card, supervisor.map)
      for {
        i <- cells.indices
        j <- cells.indices
      } {
        if (nums(i)(j) == 1)
          highlightCell(i, j)
      }
    case event: WaitEvent =>
      supervisor.newRound()

  }
}
