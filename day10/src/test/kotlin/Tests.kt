import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    private val largeInput = (".#..##.###...#######\n" +
            "##.############..##.\n" +
            ".#.######.########.#\n" +
            ".###.#######.####.#.\n" +
            "#####.##.#.##.###.##\n" +
            "..#####..#.#########\n" +
            "####################\n" +
            "#.####....###.#.#.##\n" +
            "##.#################\n" +
            "#####.##.###..####..\n" +
            "..######..##.#######\n" +
            "####.##.####...##..#\n" +
            ".#####..#.######.###\n" +
            "##...#.##########...\n" +
            "#.##########.#######\n" +
            ".####.#.###.###.#.##\n" +
            "....##.##.###..#####\n" +
            ".#.#.###########.###\n" +
            "#.#.#.#####.####.###\n" +
            "###.##.####.##.#..##").split("\n")

    @Test
    fun numberOfAsteroidsDetectedByBestLocation1() {
        val input = (".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##").split("\n")

        val output = calculateBestLocationAndNumberDetected(input)
        val expectedLocation = GridPoint2d(3, 4)
        val numberDetected = 8

        assertEquals(output, Pair(expectedLocation, numberDetected))
    }

    @Test
    fun numberOfAsteroidsDetectedByBestLocation2() {
        val input = ("......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####").split("\n")

        val output = calculateBestLocationAndNumberDetected(input)
        val expectedLocation = GridPoint2d(5, 8)
        val numberDetected = 33

        assertEquals(output, Pair(expectedLocation, numberDetected))
    }

    @Test
    fun numberOfAsteroidsDetectedByBestLocation3() {
        val input = ("#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###.").split("\n")

        val output = calculateBestLocationAndNumberDetected(input)
        val expectedLocation = GridPoint2d(1, 2)
        val numberDetected = 35

        assertEquals(output, Pair(expectedLocation, numberDetected))
    }

    @Test
    fun numberOfAsteroidsDetectedByBestLocation4() {
        val input = (".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#..").split("\n")

        val output = calculateBestLocationAndNumberDetected(input)
        val expectedLocation = GridPoint2d(6, 3)
        val numberDetected = 41

        assertEquals(output, Pair(expectedLocation, numberDetected))
    }

    @Test
    fun numberOfAsteroidsDetectedByBestLocation5() {
        val output = calculateBestLocationAndNumberDetected(largeInput)
        val expectedLocation = GridPoint2d(11, 13)
        val numberDetected = 210

        assertEquals(output, Pair(expectedLocation, numberDetected))
    }

    @Test
    fun firstVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 1)
        val expected = 11 * 100 + 12

        assertEquals(output, expected)
    }

    @Test
    fun secondVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 2)
        val expected = 12 * 100 + 1

        assertEquals(output, expected)
    }

    @Test
    fun thirdVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 3)
        val expected = 12 * 100 + 2

        assertEquals(output, expected)
    }

    @Test
    fun tenthVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 10)
        val expected = 12 * 100 + 8

        assertEquals(output, expected)
    }

    @Test
    fun twentiethVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 20)
        val expected = 16 * 100 + 0

        assertEquals(output, expected)
    }

    @Test
    fun fiftiethVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 50)
        val expected = 16 * 100 + 9

        assertEquals(output, expected)
    }

    @Test
    fun oneHundredthVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 100)
        val expected = 10 * 100 + 16

        assertEquals(output, expected)
    }

    @Test
    fun oneHundredAndNinetyNinthVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 199)
        val expected = 9 * 100 + 6

        assertEquals(output, expected)
    }

    @Test
    fun twoHundredthVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 200)
        val expected = 8 * 100 + 2

        assertEquals(output, expected)
    }

    @Test
    fun twoHundredAndFirstVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 201)
        val expected = 10 * 100 + 9

        assertEquals(output, expected)
    }

    @Test
    fun twoHundredAndNinetyNinthVaporizedAsteroid() {
        val output = calculateResultGivenLocation(largeInput, 299)
        val expected = 11 * 100 + 1

        assertEquals(output, expected)
    }
}

