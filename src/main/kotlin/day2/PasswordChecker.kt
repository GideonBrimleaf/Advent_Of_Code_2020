package day2

import utils.Breakdownable

class PasswordChecker(private val minimumQty: Int, private val maximumQty : Int, private val character : Char, val password : String): Breakdownable by Breakdownable.DataBreakdown {
    constructor(passwordPolicy: List<String>): this (
        minimumQty = passwordPolicy[0].toInt(),
        maximumQty = passwordPolicy[1].toInt(),
        character = passwordPolicy[2].toCharArray()[0],
        password = passwordPolicy[3]
    )

    fun isValidPart1(): Boolean {
        val passwordBreakdown = getBreakdown(password)
        return passwordBreakdown.containsKey(character)
                && passwordBreakdown[character]!! >= minimumQty
                && passwordBreakdown[character]!! <= maximumQty
    }

    fun isValidPart2(): Boolean {
        val passwordSubSet = password.slice(listOf(minimumQty - 1, maximumQty - 1))
        val passwordSubsetBreakdown = getBreakdown(passwordSubSet)
        return passwordSubSet.contains(character) && passwordSubsetBreakdown[character]!! < 2
    }

}


