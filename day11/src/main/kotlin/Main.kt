fun main() {
    val inputAsList = resourceFile("input.txt")
        .readLines()[0]
        .split(',')
        .map(String::toLong)

    val inputAsMap = convertInputToMap(inputAsList)

    println("Part I: the solution is ${solvePartI(inputAsMap, Robot.PanelColor.BLACK)}.")
    println("Part II: the solution is")
    solvePartII(inputAsMap, Robot.PanelColor.WHITE)
}

fun solvePartI(input: Map<Long, Long>, startingColor: Robot.PanelColor): Int {
    val robot = Robot(input, startingColor)
    return robot.getPaintedPanels().size
}

fun solvePartII(input: Map<Long, Long>, startingColor: Robot.PanelColor) {
    val robot = Robot(input, startingColor)
    robot.paint()
    robot.showRegistrationId()
}

fun convertInputToMap(inputAsInts: List<Long>): Map<Long, Long> {
    val result = mutableMapOf<Long, Long>().withDefault { 0 }
    for (i in inputAsInts.indices) {
        result[i.toLong()] = inputAsInts[i]
    }

    return result.toMap()
}
