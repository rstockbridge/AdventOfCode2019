fun main() {
    val inputAsInts = resourceFile("input.txt")
        .readLines()
        .map(String::toInt)

    println("Part I: the solution is ${solvePartI(inputAsInts)}.")
    println("Part II: the solution is ${solvePartII(inputAsInts)}.")
}

fun solvePartI(inputAsInts: List<Int>): Int {
    return calculateTotalFuel(inputAsInts)
}

fun solvePartII(inputAsInts: List<Int>): Int {
    return calculateTotalFuelWithAddedFuel(inputAsInts)
}

fun calculateTotalFuel(masses: List<Int>): Int {
    return masses.map { calculateModuleFuel(it) }.sum()
}

fun calculateModuleFuel(mass: Int): Int {
    return mass / 3 - 2;
}

fun calculateTotalFuelWithAddedFuel(masses: List<Int>): Int {
    return masses.map { calculateModuleFuelWithAddedFuel(it) }.sum()
}

fun calculateModuleFuelWithAddedFuel(mass: Int): Int {
    var result = 0
    var fuelRequirement = calculateModuleFuel(mass)

    while (fuelRequirement > 0) {
        result += fuelRequirement
        fuelRequirement = calculateModuleFuel(fuelRequirement)
    }

    return result
}
