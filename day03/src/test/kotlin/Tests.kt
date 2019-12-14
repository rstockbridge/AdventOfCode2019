import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun closestDistance1() {
        val wire1 = parseInput("R8,U5,L5,D3")
        val wire2 = parseInput("U7,R6,D4,L4")
        val output = calculateDistanceToClosestIntersectionPoint(wire1, wire2)

        Assertions.assertEquals(output, 6)
    }

    @Test
    fun closestDistance2() {
        val wire1 = parseInput("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val wire2 = parseInput("U62,R66,U55,R34,D71,R55,D58,R83")
        val output = calculateDistanceToClosestIntersectionPoint(wire1, wire2)

        Assertions.assertEquals(output, 159)
    }

    @Test
    fun closestDistance3() {
        val wire1 = parseInput("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val wire2 = parseInput("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
        val output = calculateDistanceToClosestIntersectionPoint(wire1, wire2)

        Assertions.assertEquals(output, 135)
    }

    @Test
    fun minTotalSteps1() {
        val wire1 = parseInput("R8,U5,L5,D3")
        val wire2 = parseInput("U7,R6,D4,L4")
        val output = calculateMinTotalNumberOfSteps(wire1, wire2)

        Assertions.assertEquals(output, 30)
    }

    @Test
    fun minTotalSteps2() {
        val wire1 = parseInput("R75,D30,R83,U83,L12,D49,R71,U7,L72")
        val wire2 = parseInput("U62,R66,U55,R34,D71,R55,D58,R83")
        val output = calculateMinTotalNumberOfSteps(wire1, wire2)

        Assertions.assertEquals(output, 610)
    }

    @Test
    fun minTotalSteps3() {
        val wire1 = parseInput("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51")
        val wire2 = parseInput("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
        val output = calculateMinTotalNumberOfSteps(wire1, wire2)

        Assertions.assertEquals(output, 410)
    }
}
