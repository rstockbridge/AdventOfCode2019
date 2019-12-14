import extensions.charCounts

fun main() {
    val input = resourceFile("input.txt")
        .readText()

    val image = parseInput(input, 25, 6)

    println("Part I: the solution is ${solvePartI(image)}.")
    println("Part II: the solution is:")
    solvePartII(image)
}

fun parseInput(input: String, width: Int, height: Int): List<List<String>> {
    val result = mutableListOf<List<String>>()

    val numberOfLayers = input.length / (width * height)
    val layerSize = height * width

    for (layerIndex in 0 until numberOfLayers) {
        val layer = mutableListOf<String>()

        for (rowIndex in 0 until height) {
            val row = input.substring(
                layerIndex * layerSize + width * rowIndex,
                layerIndex * layerSize + width * (1 + rowIndex)
            )
            layer.add(row)
        }

        result.add(layer)
    }

    return result
}

fun solvePartI(image: List<List<String>>): Int {
    return validateImage(image)
}

fun solvePartII(image: List<List<String>>) {
    val height = image[0].size
    val width = image[0][0].length

    for (heightIndex in 0 until height) {
        for (widthIndex in 0 until width) {

            var layerIndex = 0

            var layerPixel = image[layerIndex][heightIndex][widthIndex]

            while (layerPixel == '2') {
                layerIndex++
                layerPixel = image[layerIndex][heightIndex][widthIndex]
            }

            if (layerPixel == '0') {
                print(" ")
            } else {
                print("█")
            }
        }
        println()
    }
}

fun validateImage(image: List<List<String>>): Int {
    val layerWithMin0Digits = image.minBy { convertLayerToSingleString(it).charCounts()['0']!! }!!

    val numberOf1Digits = convertLayerToSingleString(layerWithMin0Digits).charCounts().getOrElse('1') { 0 }
    val numberOf2Digits = convertLayerToSingleString(layerWithMin0Digits).charCounts().getOrElse('2') { 0 }

    return numberOf1Digits * numberOf2Digits
}

fun convertLayerToSingleString(layer: List<String>): String {
    var result = ""

    layer.forEach { row ->
        result += row
    }

    return result

}
