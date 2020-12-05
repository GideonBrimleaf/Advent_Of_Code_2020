class PasswordChecker(val minimumQty: Int, val maximumQty : Int, val character : Char, val password : String) {
    fun isValid(): Boolean {
        val passwordBreakdown = getBreakdown()
        return passwordBreakdown.containsKey(character)
                && passwordBreakdown.get(character)!! >= minimumQty
                && passwordBreakdown.get(character)!! <= maximumQty
    }

    fun getBreakdown(): MutableMap<Char, Int> {
        return password.fold(mutableMapOf(), { acc, letter ->
            if (acc.get(letter) == null) {
                acc.put(letter, 1)
            } else {
                val existingValue = acc.get(letter)
                acc.set(letter, existingValue!! + 1)
            }
            acc
        })
    }
}


