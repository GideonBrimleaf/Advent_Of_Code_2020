class PasswordChecker(private var minimumQty: Int, private var maximumQty : Int, private var character : Char, private var password : String) {
    constructor(passwordPolicy: List<String>): this (
        minimumQty = passwordPolicy[0].toInt(),
        maximumQty = passwordPolicy[1].toInt(),
        character = passwordPolicy[2].toCharArray()[0],
        password = passwordPolicy[3]
    )

    fun isValid(): Boolean {
        val passwordBreakdown = getBreakdown()
        return passwordBreakdown.containsKey(character)
                && passwordBreakdown[character]!! >= minimumQty
                && passwordBreakdown[character]!! <= maximumQty
    }

    fun getBreakdown(): MutableMap<Char, Int> {
        return password.fold(mutableMapOf(), { acc, letter ->
            if (acc[letter] == null) {
                acc[letter] = 1
            } else {
                acc[letter] = acc[letter]!! + 1
            }
            acc
        })
    }

}


