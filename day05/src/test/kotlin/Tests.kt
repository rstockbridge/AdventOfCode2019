import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun calculateOpcodeInstruction3() {
        val instruction = 3
        val opcode = calculateOpcode(instruction)
        val expectedOutput = 3

        assertEquals(opcode, expectedOutput)
    }

    @Test
    fun calculateOpcodeInstruction1001() {
        val instruction = 1001
        val opcode = calculateOpcode(instruction)
        val expectedOutput = 1

        assertEquals(opcode, expectedOutput)
    }

    @Test
    fun calculateOpcodeInstruction1002() {
        val instruction = 1002
        val opcode = calculateOpcode(instruction)
        val expectedOutput = 2

        assertEquals(opcode, expectedOutput)
    }

    @Test
    fun calculateParameterModesInstruction3() {
        val instruction = 3
        val parameterModes = calculateParameterModes(instruction, calculateOpcode(instruction))
        val expectedOutput = listOf(0)

        assertEquals(parameterModes, expectedOutput)
    }

    @Test
    fun calculateParameterModesInstruction1001() {
        val instruction = 1001
        val parameterModes = calculateParameterModes(instruction, calculateOpcode(instruction))
        val expectedOutput = listOf(0, 1, 0)

        assertEquals(parameterModes, expectedOutput)
    }

    @Test
    fun calculateParameterModesInstruction1002() {
        val instruction = 1002
        val parameterModes = calculateParameterModes(instruction, calculateOpcode(instruction))
        val expectedOutput = listOf(0, 1, 0)

        assertEquals(parameterModes, expectedOutput)
    }

    @Test
    fun program1PartI() {
        val programInput = 1
        val state = listOf(3, 0, 4, 0, 99)
        val ioProcessor = MyIOProcessor(programInput)
        val output = runComputer(state, ioProcessor)
        val expectedOtuput = programInput

        assertEquals(output, expectedOtuput)
    }

    @Test
    fun program1PartIIInputLessThan8() {
        val programInput = 7
        val state = listOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        )
        val ioProcessor = MyIOProcessor(programInput)
        val output = runComputer(state, ioProcessor)
        val expectedOutput = 999

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program1PartIIInputEqual8() {
        val programInput = 8
        val state = listOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        )
        val ioProcessor = MyIOProcessor(programInput)
        val output = runComputer(state, ioProcessor)
        val expectedOutput = 1000

        assertEquals(output, expectedOutput)
    }

    @Test
    fun program1PartIIInputGreaterThan8() {
        val programInput = 9
        val state = listOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        )
        val ioProcessor = MyIOProcessor(programInput)
        val output = runComputer(state, ioProcessor)
        val expectedOutput = 1001

        assertEquals(output, expectedOutput)
    }
}
