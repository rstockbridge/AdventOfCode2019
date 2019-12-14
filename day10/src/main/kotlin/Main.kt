import kotlin.math.atan2

fun main() {
    val input = resourceFile("input.txt")
        .readLines()

    println("Part I: the solution is ${solvePartI(input)}.")
    println("Part II: the solution is ${solvePartII(input)}.")
}

fun solvePartI(input: List<String>): Int {
    return calculateBestLocationAndNumberDetected(input).second
}

fun solvePartII(input: List<String>): Int {
    return calculateResultGivenLocation(input, 200)
}

fun calculateBestLocationAndNumberDetected(asteroidMap: List<String>): Pair<GridPoint2d, Int> {
    val height = asteroidMap.size
    val width = asteroidMap[0].length

    val asteroidLocationAndNumberItCanDetect = mutableMapOf<GridPoint2d, Int>()

    for (y in 0 until height) {
        for (x in 0 until width) {
            if (asteroidMap[y][x] == '#') {
                val asteroidLocation = GridPoint2d(x, y)
                asteroidLocationAndNumberItCanDetect[asteroidLocation] =
                    calculateNumberOfAsteroidsDetected(asteroidLocation, asteroidMap)
            }
        }
    }

    return asteroidLocationAndNumberItCanDetect.maxBy { it.value }!!.toPair()
}

fun calculateNumberOfAsteroidsDetected(location: GridPoint2d, asteroidMap: List<String>): Int {
    val closestDistanceByAngle = mutableMapOf<Double, Double>()

    val height = asteroidMap.size
    val width = asteroidMap[0].length

    for (y in 0 until height) {
        for (x in 0 until width) {
            val otherLocation = GridPoint2d(x, y)

            if (asteroidMap[y][x] != '#' || location == otherLocation) {
                continue
            }

            val angle = calculateAngle(location, otherLocation)
            val distance = location.l2DistanceTo(otherLocation)

            if (!closestDistanceByAngle.containsKey(angle)) {
                closestDistanceByAngle[angle] = distance
            } else {
                if (closestDistanceByAngle[angle]!! > distance) {
                    closestDistanceByAngle[angle] = distance
                }
            }
        }
    }

    return closestDistanceByAngle.size
}

private fun calculateAngle(location: GridPoint2d, other: GridPoint2d): Double {
    val untransformedAngle = atan2((location.x - other.x).toDouble(), (location.y - other.y).toDouble())

    return if (untransformedAngle < 0) {
        -untransformedAngle
    } else if (untransformedAngle > 0 && untransformedAngle < Math.PI) {
        (Math.PI - untransformedAngle) + Math.PI
    } else {
        untransformedAngle
    }
}

fun calculateResultGivenLocation(asteroidMap: List<String>, rank: Int): Int {
    val bestLocation = calculateBestLocationAndNumberDetected(asteroidMap).first
    val chosenVaporizedAsteroid = calculateVaporizedAsteroidLocationGivenRank(bestLocation, asteroidMap, rank)
    return 100 * chosenVaporizedAsteroid.x + chosenVaporizedAsteroid.y
}

fun calculateVaporizedAsteroidLocationGivenRank(
    bestLocation: GridPoint2d,
    asteroidMap: List<String>,
    rank: Int
): GridPoint2d {
    val groupedDistanceLocationPairsByAngle =
        groupDistanceLocationPairsByAngle(bestLocation, asteroidMap, rank).toSortedMap()
    val vaporizedAsteroids = mutableListOf<Pair<Int, Pair<Double, GridPoint2d>>>()

    groupedDistanceLocationPairsByAngle.values.forEach { distanceLocationPairs ->
        distanceLocationPairs.sortBy { it.first }
        distanceLocationPairs.withIndex().forEach { (index, value) ->
            vaporizedAsteroids.add(Pair(index, value))
        }
    }

    vaporizedAsteroids.sortBy { it.first }

    return vaporizedAsteroids[rank - 1].second.second
}

private fun groupDistanceLocationPairsByAngle(
    bestLocation: GridPoint2d,
    asteroidMap: List<String>,
    rank: Int
): MutableMap<Double, MutableList<Pair<Double, GridPoint2d>>> {
    val result = mutableMapOf<Double, MutableList<Pair<Double, GridPoint2d>>>()

    val height = asteroidMap.size
    val width = asteroidMap[0].length

    for (y in 0 until height) {
        for (x in 0 until width) {
            val otherLocation = GridPoint2d(x, y)

            if (asteroidMap[y][x] != '#' || bestLocation == otherLocation) {
                continue
            }

            val angle = calculateAngle(bestLocation, otherLocation)
            val distance = bestLocation.l2DistanceTo(otherLocation)

            if (result.containsKey(angle)) {
                result[angle]!!.add(Pair(distance, otherLocation))
            } else {
                result[angle] = mutableListOf(Pair(distance, otherLocation))
            }
        }
    }

    return result
}
