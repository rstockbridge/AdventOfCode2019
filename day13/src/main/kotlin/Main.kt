fun main() {
    val inputAsList = resourceFile("input.txt")
        .readLines()[0]
        .split(',')
        .map(String::toLong)

    val inputAsMap = convertInputToMap(inputAsList)

    println("Part I: the solution is ${solvePartI(inputAsMap)}.")
    println("Part II: the solution is ${solvePartII(inputAsMap, 2.toLong())}.")
}

fun solvePartI(input: Map<Long, Long>): Int {
    val game = Game(input)
    return game
        .getTiles()
        .filter { tile ->
            tile.value == TileType.BLOCK
        }
        .size
}

fun solvePartII(input: Map<Long, Long>, numberOfQuarters: Long): Int {
    val game = Game(input)
    return game.play(numberOfQuarters)
}

fun convertInputToMap(inputAsInts: List<Long>): Map<Long, Long> {
    val result = mutableMapOf<Long, Long>().withDefault { 0 }
    for (i in inputAsInts.indices) {
        result[i.toLong()] = inputAsInts[i]
    }

    return result.toMap()
}
