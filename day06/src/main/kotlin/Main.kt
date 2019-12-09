fun main() {
    val inputAsLines = resourceFile("input.txt")
        .readLines()

    val directOrbits = parseInput(inputAsLines)

    println("Part I: the solution is ${solvePartI(directOrbits)}.")
    println("Part II: the solution is ${solvePartII(directOrbits)}.")
}

fun parseInput(inputAsLines: List<String>): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()

    for (line in inputAsLines) {
        val splitInput = line.split(")")
        result.add(Pair(splitInput[0], splitInput[1]))
    }

    return result
}

fun solvePartI(directOrbits: List<Pair<String, String>>): Int {
    return calculateNumberOfOrbits(directOrbits);
}

fun solvePartII(directOrbits: List<Pair<String, String>>): Int {
    return calculateMinNumberOfOrbitalTransfers(directOrbits)
}

fun calculateNumberOfOrbits(directOrbits: List<Pair<String, String>>): Int {
    val tree = addChildrenToParent(tree("COM") {}, directOrbits)

    return calculateNumberOfOrbitsFromTree(tree)
}

fun calculateNumberOfOrbitsFromTree(tree: Tree<String>, depth: Int = 1): Int {
    var result = 0

    for (child in tree.children) {
        result += depth + calculateNumberOfOrbitsFromTree(child, depth + 1)
    }

    return result
}

fun calculateMinNumberOfOrbitalTransfers(directOrbits: List<Pair<String, String>>): Int {
    val youAncestry = findAncestry("YOU", directOrbits)
    val sanAncestry = findAncestry("SAN", directOrbits)

    val sharedAncestrySize = youAncestry.intersect(sanAncestry).size
    return (youAncestry.size - sharedAncestrySize) + (sanAncestry.size - sharedAncestrySize)
}

fun findAncestry(spaceObject: String, directOrbits: List<Pair<String, String>>): List<String> {
    val result = mutableListOf<String>()

    var parent = findParent(spaceObject, directOrbits)

    while (parent != "COM") {
        result.add(parent)
        parent = findParent(parent, directOrbits)
    }

    return result
}

fun findParent(spaceObject: String, directOrbits: List<Pair<String, String>>): String {
    for ((orbited, orbiter) in directOrbits) {
        if (orbiter == spaceObject) {
            return orbited
        }
    }

    throw IllegalStateException("This line should not be reached.")
}

fun addChildrenToParent(parent: MutableTree<String>, directOrbits: List<Pair<String, String>>): MutableTree<String> {
    val orbited = parent.data
    val listOfOrbiters = getOrbiter(orbited, directOrbits)

    for (orbiter in listOfOrbiters) {
        val child = tree(orbiter)
        parent.addChild(child)
        addChildrenToParent(parent = child, directOrbits = directOrbits)
    }

    return parent
}

fun getOrbiter(targetOrbited: String, directOrbits: List<Pair<String, String>>): List<String> {
    val result = mutableListOf<String>()

    for ((orbited, orbiter) in directOrbits) {
        if (orbited == targetOrbited) {
            result.add(orbiter)
        }
    }

    return result
}
