package aview.GUI

import aview.GUI
import gamecontrol.controller.{ControllerInterface, Controller}
import gamecontrol.supervisor.SupervisorInterface
import gamecontrol._
import gamemodel.model.CardInterface
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JFrame, JOptionPane, JPanel}

import scala.swing._
import scala.swing.event.ButtonClicked


class GUI(supervisor:SupervisorInterface, controller: ControllerInterface, showRound:Boolean) extends MainFrame {

  title = "Square Castle"
  background = java.awt.Color.WHITE
  preferredSize = new Dimension(1000, 700)

  var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](supervisor.map.field.size, supervisor.map.field.size)
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

    rotateRight.background = java.awt.Color.WHITE
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

    listenTo(rotateRight, rotateLeft, tipp,pause)
    val buttons: List[Button] = List(rotateRight, rotateLeft, tipp,pause)
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
          publish(new TippEvent)
          supervisor.newRoundactive()
        }
          if(b == pause){
            controller.befehl = "wait"
            supervisor.newRoundactive()
          }
        //draw()
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

    //var statusline = new TextField(gamecontrol.controller.gameStatus.toString, 1)
    var nameField = new TextField("Name")
    nameField.preferredSize = new Dimension(45, 30)
    nameField.font = new Font("Verdana", 1, 35)
    //nameField.columns = 45
    nameField.horizontalAlignment = Alignment.Center
    nameField.editable = false
    nameField.background = java.awt.Color.WHITE


    //var credits = new TextField(gamecontrol.controller.players("Punkte: " + gamecontrol.controller.gameStatus.id).credits.toString, 1)
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

    //var playerName = gamecontrol.controller.supervisor.playersturn.toString()
    //var playerPoints = gamecontrol.controller.supervisor.playersturn.Punkte.toString
    val playerLabel = new TextField("Players turn:")
    val cardLabel = new Label()
    val remaining = new TextField("Remaining rounds:")
    val rundenLabel = new TextField("" + supervisor.rounds / 2)

    var nameLabel:TextField =_
    var pp1:TextField =_
    var pointsLabel:TextField =_
    var pp2:TextField=_
    var pointsLabel2: TextField =_

    val imgPanel = new JPanel()

    if(supervisor.p1 != null) {
      nameLabel = new TextField(supervisor.p1.toString())
      pp1 = new TextField(supervisor.p1.toString() + "'s Points:")
      pointsLabel = new TextField(supervisor.p1.getPoints().toString)
    } else {
      nameLabel = new TextField(supervisor.bot2.toString())
      pp1 = new TextField(supervisor.bot2.toString() + "'s Points:")
      pointsLabel = new TextField(supervisor.bot2.getPoints().toString)

    }

    if(supervisor.p2 != null) {
      pp2 = new TextField(supervisor.p2.toString() + "'s Points:")
      pointsLabel2 = new TextField(supervisor.p2.getPoints().toString)
    } else {
      pp2 = new TextField(supervisor.bot.toString() + "'s Points:")
      pointsLabel2 = new TextField(supervisor.bot.getPoints().toString)
    }

    imgPanel.setAlignmentX(4)
    imgPanel.setAlignmentY(4)

    playerLabel.background = java.awt.Color.WHITE
    playerLabel.horizontalAlignment = Alignment.Center
    playerLabel.editable = false

    nameLabel.background = java.awt.Color.WHITE
    nameLabel.foreground = java.awt.Color.BLUE
    nameLabel.preferredSize = new Dimension(150, 100)
    nameLabel.horizontalAlignment = Alignment.Center
    nameLabel.editable = false
    //nameLabel.verticalAlignment = Alignment.Center
    //nameLabel.verticalTextPosition = Alignment.Bottom

    pp1.background = java.awt.Color.WHITE
    pp1.horizontalAlignment = Alignment.Center
    pp1.editable = false
    pp2.background = java.awt.Color.WHITE
    pp2.horizontalAlignment = Alignment.Center
    pp2.editable = false

    pointsLabel.background = java.awt.Color.WHITE
    //pointsLabel.preferredSize = new Dimension(150, 100)
    pointsLabel.horizontalAlignment = Alignment.Center
    pointsLabel.editable = false

    pointsLabel2.background = java.awt.Color.WHITE
    pointsLabel2.horizontalAlignment = Alignment.Center
    pointsLabel2.editable = false



    cardLabel.background = java.awt.Color.WHITE
    cardLabel.preferredSize = new Dimension(150, 190)
    cardLabel.verticalAlignment = Alignment.Center

    rundenLabel.background = java.awt.Color.WHITE
    rundenLabel.horizontalAlignment = Alignment.Center
    rundenLabel.editable = false

    remaining.background = java.awt.Color.WHITE
    remaining.horizontalAlignment = Alignment.Center
    remaining.editable = false

    playerLabel.font = new Font("Verdana", 1, 15)
    nameLabel.font = new Font("Verdana", 1, 20)
    pp1.font = new Font("Verdana", 1, 15)
    pp2.font = new Font("Verdana", 1, 15)

    pointsLabel.font = new Font("Verdana", 1, 12)
    pointsLabel2.font = new Font("Verdana", 1, 12)

    cardLabel.font = new Font("Verdana", 1, 20)
    rundenLabel.font = new Font("Verdana", 1, 12)
    remaining.font = new Font("Verdana", 1, 12)

    var guicell = new GuiCell(0, 0, supervisor, controller)
    guicell.setCellPicture
    cardLabel.icon = new ImageIcon(guicell.myPicture)

    add(playerLabel, constraints(0, 1, 1, 1, 0, 0.1))

    add(nameLabel, constraints(0, 2, 1, 1, 0, 0.1))

    add(pp1, constraints(0, 3, 1, 1, 0, 0.1))

    add(pointsLabel, constraints(0, 4, 1, 1, 0, 0.1))
    add(pp2, constraints(0, 5, 1, 1, 0, 0.1))

    add(pointsLabel2, constraints(0, 6, 1, 1, 0, 0.1))

    if(!showRound) {
      add(remaining, constraints(0, 7, 1, 1, 0, 0.1))

      add(rundenLabel, constraints(0, 8, 1, 1, 0, 0.1))
    }

    add(cardLabel, constraints(0, 9, 1, 1, 0, 0.1))


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

// runden werden nicht korrekt ausgegebe, methode generel müll
  def refreshRightPanel(): Unit = {

    if(supervisor.p1 != null)
      rightPanel.pointsLabel.text = supervisor.p1.getPoints()+ " "
    else
      rightPanel.pointsLabel.text = supervisor.bot2.getPoints()+ " "

    if(supervisor.p2 != null)
      rightPanel.pointsLabel2.text = supervisor.p2.getPoints() + " "
    else
      rightPanel.pointsLabel2.text = supervisor.bot.getPoints()+ " "


      rightPanel.rundenLabel.text = "" + (supervisor.rounds / 2 + 1)

    if (supervisor.state) {
      rightPanel.nameLabel.foreground = java.awt.Color.BLUE.darker()
      if(supervisor.p1 != null)
        rightPanel.nameLabel.text = supervisor.p1.toString()
      else
        rightPanel.nameLabel.text = "BOT"
    }
    else {
      rightPanel.nameLabel.foreground = java.awt.Color.RED.darker()
      if(supervisor.p2 != null)
        rightPanel.nameLabel.text = supervisor.p2.toString()
      else
        rightPanel.nameLabel.text = "BOT"

    }

  }



  /*
  def refreshRightPanel(): Unit = {
    rightPanel.pointsLabel.text = gamecontrol.controller.supervisor.p1.getPoints() + " Points"

    if (gamecontrol.controller.supervisor.bot == null && gamecontrol.controller.supervisor.playersturn != null) {
      rightPanel.pointsLabel2.text = gamecontrol.controller.supervisor.p2.getPoints() + " Points"
      rightPanel.nameLabel.text = "" + gamecontrol.controller.supervisor.playersturn
      if (gamecontrol.controller.supervisor.runden % 2 == 0)
        rightPanel.rundenLabel.text = "" + gamecontrol.controller.supervisor.runden / 2
    } else {
      rightPanel.pointsLabel2.text = gamecontrol.controller.supervisor.bot.getPoints() + " Points"
      rightPanel.nameLabel.text = "" + gamecontrol.controller.supervisor.bot.toString()
      rightPanel.nameLabel.text = "" + gamecontrol.controller.supervisor.playersturn
      if (gamecontrol.controller.supervisor.runden % 2 == 0)
        rightPanel.rundenLabel.text = "" + gamecontrol.controller.supervisor.runden / 2
    }



    if (gamecontrol.controller.supervisor.playersturn == gamecontrol.controller.supervisor.p1)
      rightPanel.nameLabel.foreground = java.awt.Color.BLUE.darker()
    else
      rightPanel.nameLabel.foreground = java.awt.Color.RED.darker()

  }
*/


  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(scala.swing.Action("New Game") {
        val start = new startScreen(supervisor, controller)
        GUI.this.visible = false
        draw()
      })
      contents += new MenuItem(scala.swing.Action("Save") {
        supervisor.save(supervisor.map)
      })
      contents += new MenuItem(scala.swing.Action("Load") {
        supervisor.load
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
        //refreshRightPanel
      })
      contents += new Separator()
      contents += new MenuItem(scala.swing.Action("Exit") {
        System.exit(0)
      })
    }
  }

  contents = new BorderPanel {
    add(menuBar, BorderPanel.Position.North)
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
      var guicell = GUI.GuiCell(row, line, supervisor, controller)
      //KLAPPT ALLES BIS DATO
      cells(line)(row) = guicell
      listenTo(guicell)
      contents += guicell

      //PUNKTE ZÄHLEN IMMER NUR DEN ERSTEN ZWEIG, DANACH IST DIE LIST SCHON VOLL MIT DEN ALTEN KARTEN UND ER ÜBERSPRINGT
      //geht zwar die wege, aber added die werte aus den verschiedenen richtungen nicht zusammen

    }
  }
  //draw()
  visible = true


  def draw(): Unit = {
    drawhidden()
    for {
      line <- cells.indices
      row <- cells.indices
    } cells(line)(row).redrawCell
    repaint
    //refreshRightPanel
  }

  def drawhidden(): Unit = {
    for {
      line <- cells.indices
      row <- cells.indices
    } cells(line)(row).setCard(supervisor.map.field(row)(line))
  }



  def updateCard(card:CardInterface): Unit ={
    var guicell = new GuiCell(0,0, supervisor,controller)
    guicell.myCard = card
    guicell.setCellPicture
    rightPanel.cardLabel.icon = new ImageIcon(guicell.myPicture)
    //println("Karte aktualisieren")
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
    cells(x)(y).highlight
  }


  reactions += {
    case event: NewRoundEvent => //ALLES NEU MALEN(neue spieler gesetzt, neue Karte
      refreshRightPanel()
      draw()
    case event: InsertedEvent =>
      draw()
    case event: SaveEvent =>
      cells =  Array.ofDim[GuiCell](supervisor.map.field.size, supervisor.map.field.size)
      //array füllen
      repaint
      draw()
    case event: DoesntFitEvent =>
      doesntFit()
    case event: CardChangedEvent =>
      updateCard(event.newcard)
    case event: BotEvent =>
      //gamecontrol.controller.befehl = ""
      supervisor.newRoundactive()
      supervisor.otherplayer()
      draw()
      supervisor.newRound()
      //gamecontrol.controller.supervisor.newRound()

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
          highlightCell(j, i)
      }
    case event: WaitEvent =>
      supervisor.otherplayer()
      supervisor.newRound()
  }

}
