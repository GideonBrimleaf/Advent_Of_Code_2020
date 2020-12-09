package utils

interface Breakdownable {
    fun getBreakdown(password: String): MutableMap<Char, Int>
}