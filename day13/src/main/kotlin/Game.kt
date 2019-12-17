class Game(state: Map<Long, Long>) {
    private val mutableState = state.toMutableMap()
    private val tiles = mutableMapOf<GridPoint2d, TileType>().withDefault { TileType.EMPTY }
    var scores = mutableListOf<Int>()

    var x = 0
    var y = 0
    var tileId = 0
    var outputCount = 0

    private val computer = Computer(mutableState, object : IOProcessor {
        override fun getInput(): Long {
            val paddlePosition = getPaddlePosition()
            val ballPosition = getBallPosition()

            return when {
                ballPosition.x > paddlePosition.x -> 1
                ballPosition.x < paddlePosition.x -> -1
                else -> 0
            }
        }

        override fun shareOutput(output: Long) {
            if (outputCount % 3 == 0) {
                x = output.toInt()
            } else if (outputCount % 3 == 1) {
                y = output.toInt()
            } else {
                if (x == -1 && y == 0) {
                    scores.add(output.toInt())
                } else {
                    tileId = output.toInt()
                    addTile()
                }
            }

            outputCount++
        }

        override fun print() {
            printTiles()
        }
    })

    fun getTiles(): Map<GridPoint2d, TileType> {
        computer.run()
        return tiles
    }

    fun play(numberOfQuarters: Long): Int {
        mutableState[0] = numberOfQuarters
        computer.run()
        return scores.last()
    }

    private fun addTile() {
        when (tileId) {
            0 -> tiles[GridPoint2d(x, y)] = TileType.EMPTY
            1 -> tiles[GridPoint2d(x, y)] = TileType.WALL
            2 -> tiles[GridPoint2d(x, y)] = TileType.BLOCK
            3 -> tiles[GridPoint2d(x, y)] = TileType.PADDLE
            4 -> tiles[GridPoint2d(x, y)] = TileType.BALL
        }
    }

    fun printTiles() {
        val minX = tiles.keys.minBy { it.x }!!.x
        val maxX = tiles.keys.maxBy { it.x }!!.x
        val minY = tiles.keys.minBy { it.y }!!.y
        val maxY = tiles.keys.maxBy { it.y }!!.y

        for (y in minY..maxY) {
            for (x in minX..maxX) {
                when (tiles.getValue(GridPoint2d(x, y))) {
                    TileType.EMPTY -> print(" ")
                    TileType.WALL -> print("█")
                    TileType.BLOCK -> print("◻")
                    TileType.PADDLE -> print("▄")
                    TileType.BALL -> print("o")
                }
            }
            println()
        }
        println()
    }

    private fun getBallPosition(): GridPoint2d {
        for ((position, tileType) in tiles.entries) {
            if (tileType == TileType.BALL) {
                return position
            }
        }

        throw IllegalStateException("This line should not be reached")
    }

    private fun getPaddlePosition(): GridPoint2d {
        for ((position, tileType) in tiles.entries) {
            if (tileType == TileType.PADDLE) {
                return position
            }
        }

        throw IllegalStateException("This line should not be reached")
    }
}

enum class TileType { EMPTY, WALL, BLOCK, PADDLE, BALL }
