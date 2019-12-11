fun main() {
    val inputAsList = resourceFile("input.txt")
        .readLines()[0]
        .split(',')
        .map(String::toLong)

    val inputAsMap = convertInputToMap(inputAsList)

    println("Part I: the solution is ${solveProblem(inputAsMap, 1.toLong())}.")
    println("Part II: the solution is ${solveProblem(inputAsMap, 2.toLong())}.")
}

fun solveProblem(input: Map<Long, Long>, programInput: Long): Long {
    val ioProcessor = MyIOProcessor(programInput)
    return runComputer(input, ioProcessor)
}

fun convertInputToMap(inputAsInts: List<Long>): Map<Long, Long> {
    val result = mutableMapOf<Long, Long>().withDefault { 0 }
    for (i in inputAsInts.indices) {
        result[i.toLong()] = inputAsInts[i]
    }

    return result.toMap()
}

fun runComputer(state: Map<Long, Long>, ioProcessor: IOProcessor): Long {
    val mutableState = state.toMutableMap()

    var index = 0.toLong()
    var instruction = mutableState.getValue(index)
    var opcode = calculateOpcode(instruction)
    var parameterModes = calculateParameterModes(instruction, opcode)

    var relativeBase = 0.toLong()

    while (true) {
        if (opcode != 99) {
            parameterModes = calculateParameterModes(instruction, opcode)
        }

        when (opcode) {
            1 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )
                val parameter2 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 2),
                        parameterModes[1],
                        mutableState,
                        relativeBase
                    )

                val outputPosition =
                    calculateWriteParameterUsingMode(mutableState.getValue(index + 3), parameterModes[2], relativeBase)
                mutableState[outputPosition] = parameter1 + parameter2
                index += 4
            }
            2 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )
                val parameter2 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 2),
                        parameterModes[1],
                        mutableState,
                        relativeBase
                    )

                val outputPosition =
                    calculateWriteParameterUsingMode(mutableState.getValue(index + 3), parameterModes[2], relativeBase)
                mutableState[outputPosition] = parameter1 * parameter2
                index += 4
            }
            3 -> {
                val outputPosition =
                    calculateWriteParameterUsingMode(mutableState.getValue(index + 1), parameterModes[0], relativeBase)
                mutableState[outputPosition] = ioProcessor.input
                index += 2
            }
            4 -> {
                val parameter =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )

                ioProcessor.updateOutputList(parameter)
                index += 2
            }
            5 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )

                if (parameter1 != 0.toLong()) {
                    val parameter2 =
                        calculateReadParameterUsingMode(
                            mutableState.getValue(index + 2),
                            parameterModes[1],
                            mutableState,
                            relativeBase
                        )
                    index = parameter2
                } else {
                    index += 3
                }
            }
            6 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )

                if (parameter1 == 0.toLong()) {
                    val parameter2 =
                        calculateReadParameterUsingMode(
                            mutableState.getValue(index + 2),
                            parameterModes[1],
                            mutableState,
                            relativeBase
                        )
                    index = parameter2
                } else {
                    index += 3
                }
            }
            7 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )
                val parameter2 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 2),
                        parameterModes[1],
                        mutableState,
                        relativeBase
                    )

                val outputPosition =
                    calculateWriteParameterUsingMode(mutableState.getValue(index + 3), parameterModes[2], relativeBase)
                if (parameter1 < parameter2) {
                    mutableState[outputPosition] = 1
                } else {
                    mutableState[outputPosition] = 0
                }

                index += 4
            }
            8 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )
                val parameter2 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 2),
                        parameterModes[1],
                        mutableState,
                        relativeBase
                    )

                val outputPosition =
                    calculateWriteParameterUsingMode(mutableState.getValue(index + 3), parameterModes[2], relativeBase)

                if (parameter1 == parameter2) {
                    mutableState[outputPosition] = 1
                } else {
                    mutableState[outputPosition] = 0
                }

                index += 4
            }
            9 -> {
                val parameter1 =
                    calculateReadParameterUsingMode(
                        mutableState.getValue(index + 1),
                        parameterModes[0],
                        mutableState,
                        relativeBase
                    )
                relativeBase += parameter1
                index += 2
            }
            99 -> {
                ioProcessor.updateState(mutableState)
                return ioProcessor.getFinalOutput()
            }
            else -> throw IllegalStateException("This line should not be reached.")
        }

        instruction = mutableState.getValue(index)
        opcode = calculateOpcode(instruction)
        if (opcode != 99) {
            parameterModes = calculateParameterModes(instruction, opcode)
        }
    }
}

fun calculateOpcode(instruction: Long): Int {
    val instructionAsString = instruction.toString()

    return if (instructionAsString.length == 1) {
        instruction.toInt()
    } else {
        instructionAsString.substring(instructionAsString.length - 2 until instructionAsString.length).toInt()
    }
}

fun calculateParameterModes(instruction: Long, opcode: Int): List<Int> {
    val instructionAsString = instruction.toString()
    val length = instructionAsString.length

    // one parameter
    if (opcode == 3 || opcode == 4 || opcode == 9) {
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

fun calculateReadParameterUsingMode(
    parameter: Long,
    mode: Int,
    instructions: Map<Long, Long>,
    relativeBase: Long
): Long {
    return when (mode) {
        0 -> {
            instructions.getOrDefault(parameter, 0)
        }
        1 -> {
            parameter
        }
        2 -> {
            instructions.getOrDefault(parameter + relativeBase, 0)
        }
        else -> throw IllegalStateException("This line should not be reached.")
    }
}

fun calculateWriteParameterUsingMode(
    parameter: Long,
    mode: Int,
    relativeBase: Long
): Long {
    return when (mode) {
        0 -> {
            parameter
        }
        2 -> {
            parameter + relativeBase
        }
        else -> throw IllegalStateException("Trying parameter mode $mode")
    }
}

interface IOProcessor {
    val input: Long
    fun updateOutputList(output: Long)
    fun updateState(state: Map<Long, Long>)
    fun getFinalOutput(): Long
}

class MyIOProcessor(
    override val input: Long,
    private val computerOutput: ComputerOutput = ComputerOutput(mutableListOf(), mutableMapOf())
) : IOProcessor {

    override fun updateOutputList(output: Long) {
        computerOutput.outputList.add(output)
    }

    override fun updateState(state: Map<Long, Long>) {
        computerOutput.state = state
    }

    override fun getFinalOutput(): Long {
        return computerOutput.outputList.last()
    }

    data class ComputerOutput(val outputList: MutableList<Long>, var state: Map<Long, Long>)
}

