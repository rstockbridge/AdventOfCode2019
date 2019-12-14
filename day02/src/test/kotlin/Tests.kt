import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    private val ioProcessor = object : IOProcessor {
        override fun setInput(state: List<Int>, noun: Int, verb: Int): List<Int> {
            return state // no input modification
        }

        override fun getOutput(state: List<Int>): Int {
            return state[0]
        }
    }

    private val noun = 12
    private val verb = 2

    @Test
    fun program1() {
        val state = listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)
        val output = getPartISolution(state, ioProcessor, noun, verb)
        val expectedOutput = 3500

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program2() {
        val state = listOf(1, 0, 0, 0, 99)
        val output = getPartISolution(state, ioProcessor, noun, verb)
        val expectedOutput = 2

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program3() {
        val state = listOf(2, 3, 0, 3, 99)
        val output = getPartISolution(state, ioProcessor, noun, verb)
        val expectedOutput = 2

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program4() {
        val state = listOf(2, 4, 4, 5, 99, 0)
        val output = getPartISolution(state, ioProcessor, noun, verb)
        val expectedOutput = 2

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program5() {
        val state = listOf(1, 1, 1, 4, 99, 5, 6, 0, 99)
        val output = getPartISolution(state, ioProcessor, noun, verb)
        val expectedOutput = 30

        assertEquals(output, expectedOutput)
    }
}
