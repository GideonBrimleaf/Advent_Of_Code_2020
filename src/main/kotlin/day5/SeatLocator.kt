package day5

class SeatLocator(seatLocation: String) {
    val rowLocator = mapOf("Data" to seatLocation.substring(0,7), "Upper Bound" to "B", "Lower Bound" to "F")
    val columnLocator = mapOf("Data" to seatLocation.substring(7), "Upper Bound" to "R", "Lower Bound" to "L")

    fun calculatePosition(section: String, seatRange: MutableList<Int>): Int {
        var seatToFind = seatRange
        val propertyToAssess = if (section == "column") columnLocator else rowLocator

        propertyToAssess["Data"]!!.forEach {
            seatToFind = if (it.toString() == propertyToAssess["Upper Bound"]!!)
                upperBound(seatToFind)
            else
                lowerBound(seatToFind)
        }
        return seatToFind.first()
    }

    private fun upperBound(seatData: MutableList<Int>): MutableList<Int> {
        return seatData.filter { it > seatData.last() - seatData.size/2 }.toMutableList()
    }

    private fun lowerBound(seatData: MutableList<Int>): MutableList<Int> {
        return seatData.filter { it <= seatData.last() - seatData.size/2 }.toMutableList()
    }

    fun getSeatId(row: Int, column: Int): Int {
        return row * 8 + column
    }
}
