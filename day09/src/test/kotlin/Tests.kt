import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun program1PartIOutputIsMiddleNumber() {
        val programInput = 1.toLong()
        val state = convertInputToMap(listOf(104, 1125899906842624, 99))
        val ioProcessor = MyIOProcessor(programInput)
        val output = runComputer(state, ioProcessor)
        val expectedOutput = 1125899906842624

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program1PartIOutputIs16DigitNumber() {
        val programInput = 1.toLong()
        val state = convertInputToMap(listOf(1102, 34915192, 34915192, 7, 4, 7, 99, 0.toLong()))
        val ioProcessor = MyIOProcessor(programInput)
        val output = runComputer(state, ioProcessor).toString().length
        val expectedOutput = 16

        assertEquals(output, expectedOutput)
    }
}

