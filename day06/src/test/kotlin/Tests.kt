import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun numberOfOrbits() {
        val directOrbits =
            parseInput(listOf<String>("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L"))
        val output = calculateNumberOfOrbits(directOrbits)

        Assertions.assertEquals(output, 42)
    }

    @Test
    fun numberOfOrbitalTransfers() {
        val directOrbits =
            parseInput(listOf<String>("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN"))
        val output = calculateMinNumberOfOrbitalTransfers(directOrbits)

        Assertions.assertEquals(output, 4)
    }
}
