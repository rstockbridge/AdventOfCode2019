fun main() {
    val input = resourceFile("input.txt")
        .readLines()[0]
        .split(',')
        .map(String::toInt)

    val ioProcessor = object : IOProcessor {
        override fun setInput(state: List<Int>, noun: Int, verb: Int): List<Int> {
            val result = state.toMutableList()

            result[1] = noun
            result[2] = verb

            return result
        }

        override fun getOutput(state: List<Int>): Int {
            return state[0]
        }
    }

    println("Part I: the solution is ${solvePartI(input, ioProcessor, 12, 2)}.")
    println("Part II: the solution is ${solvePartII(input, ioProcessor, 19690720)}.")
}

fun solvePartI(input: List<Int>, ioProcessor: IOProcessor, noun: Int, verb: Int): Int {
    return getPartISolution(input, ioProcessor, noun, verb)
}

fun solvePartII(input: List<Int>, ioProcessor: IOProcessor, targetOutput: Int): Int {
    for (noun: Int in 0..99) {
        for (verb: Int in 0..99) {
            val updatedInput = ioProcessor.setInput(input, noun, verb)
            val finalState = runComputer(updatedInput)
            val output = ioProcessor.getOutput(finalState)

            if (output == targetOutput) {
                return 100 * noun + verb
            }
        }
    }

    throw IllegalStateException("This line should not be reached.")
}

fun getPartISolution(input: List<Int>, ioProcessor: IOProcessor, noun: Int, verb: Int): Int {
    val updatedInput = ioProcessor.setInput(input, noun, verb)
    val finalState = runComputer(updatedInput)
    return ioProcessor.getOutput(finalState)
}

interface IOProcessor {
    fun setInput(state: List<Int>, noun: Int, verb: Int): List<Int>
    fun getOutput(state: List<Int>): Int
}

private fun runComputer(state: List<Int>): List<Int> {
    val mutableState = state.toMutableList()
    var index = 0

    while (true) {
        val opcode = mutableState[index]

        when (opcode) {
            1 -> {
                val input1Position = mutableState[index + 1]
                val input2Position = mutableState[index + 2]
                val outputPosition = mutableState[index + 3]

                mutableState[outputPosition] = mutableState[input1Position] + mutableState[input2Position]
                index += 4
            }
            2 -> {
                val input1Position = mutableState[index + 1]
                val input2Position = mutableState[index + 2]
                val outputPosition = mutableState[index + 3]

                mutableState[outputPosition] = mutableState[input1Position] * mutableState[input2Position]
                index += 4
            }
            99 -> return mutableState
            else -> throw IllegalStateException("This line should not be reached.")
        }
    }
}


