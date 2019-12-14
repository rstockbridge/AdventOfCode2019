import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {

    @Test
    fun mass12FuelNoAddedFuel() {
        val mass = 12
        val fuelRequired = calculateTotalFuel(listOf(mass))

        assertEquals(fuelRequired, 2)
    }

    @Test
    fun mass14FuelNoAddedFuel() {
        val mass = 14
        val fuelRequired = calculateTotalFuel(listOf(mass))

        assertEquals(fuelRequired, 2)
    }

    @Test
    fun mass1969FuelNoAddedFuel() {
        val mass = 1969
        val fuelRequired = calculateTotalFuel(listOf(mass))

        assertEquals(fuelRequired, 654)
    }

    @Test
    fun mass100756FuelNoAddedFuel() {
        val mass = 100756
        val fuelRequired = calculateTotalFuel(listOf(mass))

        assertEquals(fuelRequired, 33583)
    }

    @Test
    fun mass14FuelWithAddedFuel() {
        val mass = 14
        val fuelRequired = calculateTotalFuelWithAddedFuel(listOf(mass))

        assertEquals(fuelRequired, 2)
    }

    @Test
    fun mass1969FuelWithAddedFuel() {
        val mass = 1969
        val fuelRequired = calculateTotalFuelWithAddedFuel(listOf(mass))

        assertEquals(fuelRequired, 966)
    }

    @Test
    fun mass100756FuelWithAddedFuel() {
        val mass = 100756
        val fuelRequired = calculateTotalFuelWithAddedFuel(listOf(mass))

        assertEquals(fuelRequired, 50346)
    }
}
