import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun partITest1() {
        val output = isValidPartI(111111)
        Assertions.assertEquals(output, true)
    }

    @Test
    fun partITest2() {
        val output = isValidPartI(223450)
        Assertions.assertEquals(output, false)
    }

    @Test
    fun partITest3() {
        val output = isValidPartI(123789)
        Assertions.assertEquals(output, false)
    }

    @Test
    fun partIITest1() {
        val output = isValidPartII(112233)
        Assertions.assertEquals(output, true)
    }

    @Test
    fun partIITest2() {
        val output = isValidPartII(123444)
        Assertions.assertEquals(output, false)
    }

    @Test
    fun partIITest3() {
        val output = isValidPartII(111122)
        Assertions.assertEquals(output, true)
    }

    @Test
    fun partIITestDoubleAtBeginning() {
        val output = isValidPartII(112345)
        Assertions.assertEquals(output, true)
    }

    @Test
    fun partIITestDoubleAtEnd() {
        val output = isValidPartII(123455)
        Assertions.assertEquals(output, true)
    }
}
