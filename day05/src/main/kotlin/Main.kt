fun main() {
    val input = resourceFile("input.txt")
        .readLines()[0]
        .split(',')
        .map(String::toInt)

    println("Part I: the solution is ${solveProblem(input, 2)}.")
    println("Part II: the solution is ${solveProblem(input, 5)}.")
}

fun solveProblem(inputAsInts: List<Int>, programInput: Int): Int {
    val ioProcessor = MyIOProcessor(programInput)
    return runComputer(inputAsInts, ioProcessor)
}

fun runComputer(state: List<Int>, ioProcessor: IOProcessor): Int {
    val mutableState = state.toMutableList()

    var index = 0
    var instruction = mutableState[index]
    var opcode = calculateOpcode(instruction)
    var parameterModes = listOf<Int>()

    while (true) {
        if (opcode != 99) {
            parameterModes = calculateParameterModes(instruction, opcode)
        }

        when (opcode) {
            1 -> {
                val parameter1 = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)
                val parameter2 = calculateParameterUsingMode(mutableState[index + 2], parameterModes[1], mutableState)
                val outputPosition = mutableState[index + 3]

                mutableState[outputPosition] = parameter1 + parameter2
                index += 4
            }
            2 -> {
                val parameter1 = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)
                val parameter2 = calculateParameterUsingMode(mutableState[index + 2], parameterModes[1], mutableState)
                val outputPosition = mutableState[index + 3]

                mutableState[outputPosition] = parameter1 * parameter2
                index += 4
            }
            3 -> {
                val outputPosition = mutableState[index + 1]
                mutableState[outputPosition] = ioProcessor.input
                index += 2
            }
            4 -> {
                val parameter = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)
                ioProcessor.updateOutputList(parameter)
                index += 2
            }
            5 -> {
                val parameter1 = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)

                if (parameter1 != 0) {
                    val parameter2 =
                        calculateParameterUsingMode(mutableState[index + 2], parameterModes[1], mutableState)
                    index = parameter2
                } else {
                    index += 3
                }
            }
            6 -> {
                val parameter1 = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)

                if (parameter1 == 0) {
                    val parameter2 =
                        calculateParameterUsingMode(mutableState[index + 2], parameterModes[1], mutableState)
                    index = parameter2
                } else {
                    index += 3
                }
            }
            7 -> {
                val parameter1 = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)
                val parameter2 = calculateParameterUsingMode(mutableState[index + 2], parameterModes[1], mutableState)
                val outputPosition = mutableState[index + 3]

                if (parameter1 < parameter2) {
                    mutableState[outputPosition] = 1
                } else {
                    mutableState[outputPosition] = 0
                }
                index += 4
            }
            8 -> {
                val parameter1 = calculateParameterUsingMode(mutableState[index + 1], parameterModes[0], mutableState)
                val parameter2 = calculateParameterUsingMode(mutableState[index + 2], parameterModes[1], mutableState)
                val outputPosition = mutableState[index + 3]

                if (parameter1 == parameter2) {
                    mutableState[outputPosition] = 1
                } else {
                    mutableState[outputPosition] = 0
                }

                index += 4
            }
            99 -> {
                ioProcessor.updateState(mutableState)
                return ioProcessor.getFinalOutput()
            }
            else -> throw IllegalStateException("This line should not be reached.")
        }

        instruction = mutableState[index]
        opcode = calculateOpcode(instruction)
        if (opcode != 99) {
            parameterModes = calculateParameterModes(instruction, opcode)
        }
    }
}

fun calculateOpcode(instruction: Int): Int {
    val instructionAsString = instruction.toString()

    return if (instructionAsString.length == 1) {
        instruction
    } else {
        instructionAsString.substring(instructionAsString.length - 2 until instructionAsString.length).toInt()
    }
}

fun calculateParameterModes(instruction: Int, opcode: Int): List<Int> {
    val instructionAsString = instruction.toString()
    val length = instructionAsString.length

    // one parameter
    if (opcode == 3 || opcode == 4) {
        val parameterMode: Int =
            if (length <= 2) {
                0
            } else {
                instructionAsString[length - 3].toString().toInt()
            }

        return listOf(parameterMode)
    }
    // two parameters
    else if (opcode == 5 || opcode == 6) {
        return when {
            length <= 2 -> {
                listOf(0, 0)
            }
            length == 3 -> {
                val parameter1Mode = instructionAsString[length - 3].toString().toInt()
                listOf(parameter1Mode, 0)
            }
            else -> {
                val parameter1Mode = instructionAsString[length - 3].toString().toInt()
                val parameter2Mode = instructionAsString[length - 4].toString().toInt()
                listOf(parameter1Mode, parameter2Mode)
            }
        }

    }
    // three parameters
    else if (opcode == 1 || opcode == 2 || opcode == 7 || opcode == 8) {
        return when {
            length <= 2 -> {
                listOf(0, 0, 0)
            }
            length == 3 -> {
                val parameter1Mode = instructionAsString[length - 3].toString().toInt()
                listOf(parameter1Mode, 0, 0)
            }
            length == 4 -> {
                val parameter1Mode = instructionAsString[length - 3].toString().toInt()
                val parameter2Mode = instructionAsString[length - 4].toString().toInt()
                listOf(parameter1Mode, parameter2Mode, 0)
            }
            else -> {
                val parameter1Mode = instructionAsString[length - 3].toString().toInt()
                val parameter2Mode = instructionAsString[length - 4].toString().toInt()
                val parameter3Mode = instructionAsString[length - 5].toString().toInt()
                listOf(parameter1Mode, parameter2Mode, parameter3Mode)
            }
        }
    } else throw IllegalStateException("This line should not be reached.")
}

fun calculateParameterUsingMode(parameter: Int, mode: Int, mutableState: List<Int>): Int {
    return if (mode == 0) {
        mutableState[parameter]
    } else {
        parameter
    }
}

interface IOProcessor {
    val input: Int
    fun updateOutputList(output: Int)
    fun updateState(state: List<Int>)
    fun getFinalOutput(): Int
}

class MyIOProcessor(
    override val input: Int,
    private val computerOutput: ComputerOutput = ComputerOutput(mutableListOf(), mutableListOf())
) : IOProcessor {

    override fun updateOutputList(output: Int) {
        computerOutput.outputList.add(output)
    }

    override fun updateState(state: List<Int>) {
        computerOutput.state = state
    }

    override fun getFinalOutput(): Int {
        return computerOutput.outputList.last()
    }

    data class ComputerOutput(val outputList: MutableList<Int>, var state: List<Int>)
}

