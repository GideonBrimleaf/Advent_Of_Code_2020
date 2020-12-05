package day3

fun tobogganTraversal(mapData : List<String>, rowMovement: Int, slopeMovement: Int): Int {
    var trees = 0
    var positionInRow = 0
    var positionOnSlope = 0
    val mapRowLimit = mapData[0].length

    for (row in mapData) {
        if (mapData[positionOnSlope][positionInRow] == '#') {
            trees += 1
        }

        positionInRow += rowMovement
        positionOnSlope += slopeMovement

        if (positionInRow >= mapRowLimit) {
            positionInRow -= mapRowLimit
        }

        if (positionOnSlope + slopeMovement > mapData.size) break
    }

    return trees
}
