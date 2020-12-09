package utils

interface Breakdownable {
    fun getBreakdown(data: String): MutableMap<Char, Int>

    companion object DataBreakdown : Breakdownable {
        override fun getBreakdown(data: String): MutableMap<Char, Int> {
            return data.fold(mutableMapOf(), { acc, letter ->
                if (acc[letter] == null) {
                    acc[letter] = 1
                } else {
                    acc[letter] = acc[letter]!! + 1
                }
                acc
            })
        }
    }
}