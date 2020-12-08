import day5.SeatLocator
import kotlin.test.Test
import kotlin.test.assertEquals

class SeatIdLocatorTest {
    private val sampleSeats = mutableListOf(SeatLocator("BFFFBBFRRR"), SeatLocator("FFFBBBFRRR"), SeatLocator("BBFFBBFRLL"), SeatLocator("FBFBBFFLLR"))

    private val planeColumns = IntArray(8) {it}.toMutableList()
    private val planeRows = IntArray(128) {it}.toMutableList()

    @Test fun `Can create a seat locator`() {
        assertEquals("BFFFBBF", sampleSeats[0].rowLocator["Data"])
        assertEquals("RRR", sampleSeats[0].columnLocator["Data"])
    }

    @Test fun `Can calculate column location for upper bounds`() {
        assertEquals(7, sampleSeats[0].calculatePosition("column", planeColumns))
    }

    @Test fun `Can calculate column location with mixing upper and lower bounds`() {
        assertEquals(4, sampleSeats[2].calculatePosition("column", planeColumns))
    }

    @Test fun `Can calculate column location for lower bounds`() {
        assertEquals(1, sampleSeats[3].calculatePosition("column", planeColumns))
    }

    @Test fun `Can calculate row location`() {
        assertEquals(70, sampleSeats[0].calculatePosition("row", planeRows))
    }

    @Test fun `Can Determine Seat Id`() {
        val seatRow = sampleSeats[2].calculatePosition("row", planeRows)
        val seatColumn = sampleSeats[2].calculatePosition("column", planeColumns)
        assertEquals(820, sampleSeats[0].getSeatId(seatRow, seatColumn))
    }
}