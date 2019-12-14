import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun validateImage1() {
        val image = parseInput("123045067089", 3, 2)
        val output = solvePartI(image)

        Assertions.assertEquals(output, 1 * 1)
    }

    @Test
    fun validateImage2() {
        val image = parseInput("553045067089", 3, 2)
        val output = solvePartI(image)

        Assertions.assertEquals(output, 0 * 0)
    }
}
