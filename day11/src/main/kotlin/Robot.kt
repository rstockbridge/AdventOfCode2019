class Robot(state: Map<Long, Long>, startingColor: PanelColor) {
    private var gridDirection: GridDirection = GridDirection.N
    private var currentLocation = GridPoint2d(0, 0)
    private val panels = mutableMapOf<GridPoint2d, PanelColor>().withDefault { PanelColor.BLACK }
    var outputIsColor = true

    private val computer = Computer(state, object : IOProcessor {
        override fun getInput(): Long {
            return when (panels.getValue(currentLocation)) {
                PanelColor.BLACK -> 0
                PanelColor.WHITE -> 1
            }
        }

        override fun shareOutput(output: Long) {
            if (outputIsColor) {
                paintPanel(if (output == 0.toLong()) PanelColor.BLACK else PanelColor.WHITE)
            } else {
                turnRobot(if (output == 0.toLong()) RobotTurn.LEFT else RobotTurn.RIGHT)
            }

            outputIsColor = !outputIsColor
        }
    })

    init {
        panels[currentLocation] = startingColor
    }

    fun getPaintedPanels(): Map<GridPoint2d, PanelColor> {
        paint()
        return panels
    }

    fun paint() {
        computer.run()
    }

    private fun paintPanel(panelColor: PanelColor) {
        panels[currentLocation] = panelColor
    }

    private fun turnRobot(robotTurn: RobotTurn) {
        gridDirection = if (robotTurn == RobotTurn.LEFT) {
            gridDirection.left90()
        } else {
            gridDirection.right90()
        }

        stepForward()
    }

    private fun stepForward() {
        currentLocation = when (gridDirection) {
            GridDirection.N -> currentLocation.shiftBy(dy = 1)
            GridDirection.E -> currentLocation.shiftBy(dx = 1)
            GridDirection.S -> currentLocation.shiftBy(dy = -1)
            GridDirection.W -> currentLocation.shiftBy(dx = -1)
        }
    }

    fun showRegistrationId() {
        val minX = panels.keys.minBy { it.x }!!.x
        val maxX = panels.keys.maxBy { it.x }!!.x
        val minY = panels.keys.minBy { it.y }!!.y
        val maxY = panels.keys.maxBy { it.y }!!.y

        for (y in maxY downTo minY) {
            for (x in minX..maxX) {
                if (panels.getValue(GridPoint2d(x, y)) == PanelColor.BLACK) {
                    print(" ")
                } else {
                    print("█")
                }
            }
            println()
        }
    }

    enum class PanelColor { BLACK, WHITE }

    enum class RobotTurn { LEFT, RIGHT }
}
