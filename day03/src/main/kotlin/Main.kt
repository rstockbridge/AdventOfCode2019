fun main() {
    val inputAsLines = resourceFile("input.txt")
        .readLines()

    val wire1 = parseInput(inputAsLines[0])
    val wire2 = parseInput(inputAsLines[1])


    println("Part I: the solution is ${solvePartI(wire1, wire2)}.")
    println("Part II: the solution is ${solvePartII(wire1, wire2)}.")
}

fun parseInput(inputLine: String): Wire {
    val result = Wire()
    val startingPoint = GridPoint2d.origin
    val instructionList = inputLine.split(",")

    var x = startingPoint.x
    var y = startingPoint.y

    instructionList.forEach { instruction ->
        val gridDirection = getGridDirection(instruction[0]);
        val magnitude = instruction.substring(1).toInt()

        for (i in 1..magnitude) { // exclude starting point
            when (gridDirection) {
                GridDirection.N -> y += 1
                GridDirection.E -> x += 1
                GridDirection.S -> y -= 1
                GridDirection.W -> x -= 1
            }

            result.addPoint(GridPoint2d(x, y))
        }
    }

    return result
}

private fun getGridDirection(direction: Char): Any {
    return when (direction) {
        'U' -> GridDirection.N
        'D' -> GridDirection.S
        'L' -> GridDirection.W
        'R' -> GridDirection.E
        else -> throw IllegalStateException("This line should not be reached.")
    }
}

fun solvePartI(wire1: Wire, wire2: Wire): Int {
    return calculateDistanceToClosestIntersectionPoint(wire1, wire2)
}

fun solvePartII(wire1: Wire, wire2: Wire): Int {
    return calculateMinTotalNumberOfSteps(wire1, wire2)
}

fun calculateDistanceToClosestIntersectionPoint(wire1: Wire, wire2: Wire): Int {
    val startingPoint = GridPoint2d.origin
    val sharedPoints = getSharedPoints(wire1, wire2)
    val closestPoint = sharedPoints.minBy { it.l1DistanceTo(startingPoint) }!!

    return closestPoint.l1DistanceTo(startingPoint)
}

private fun getSharedPoints(wire1: Wire, wire2: Wire): Set<GridPoint2d> {
    return wire1.getPoints().intersect(wire2.getPoints())
}

fun calculateMinTotalNumberOfSteps(wire1: Wire, wire2: Wire): Int {
    val sharedPoints = getSharedPoints(wire1, wire2)

    return sharedPoints
        .map { sharedPoint ->
            val wire1Steps = wire1
                .getPoints()
                .indexOfFirst { wire1Point ->
                    wire1Point == sharedPoint
                }
            val wire2Steps = wire2
                .getPoints()
                .indexOfFirst { wire2Point ->
                    wire2Point == sharedPoint
                }

            wire1Steps + wire2Steps + 2 // need to add on 1 + 1 to account for starting point
        }
        .min()!!
}


