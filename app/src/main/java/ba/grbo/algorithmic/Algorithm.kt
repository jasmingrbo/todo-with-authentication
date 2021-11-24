package ba.grbo.algorithmic

// Note: Check out the automated tests in AlgorithmTest.kt
fun getPair(pairs: Array<Pair>): Pair {
    require(pairs.isNotEmpty()) { MSG_PAIRS_EMPTY }
    require(pairs.map(Pair::value).all { value -> value >= 0 }) { MSG_VALUES_NEGATIVE }

    val ids = pairs.map(Pair::id)
    require(ids.size == ids.toSet().size) { MSG_IDS_NOT_UNIQUE }

    val value = pairs
        .groupBy(Pair::value)
        .firstNotNullOfOrNull { (value, pairs) -> if (pairs.size > 1) value else null }
    requireNotNull(value) { MSG_VALUES_UNIQUE }

    return Pair(
        id = ids.maxOf { it } + 1,
        value = getValidValue(value, pairs.map(Pair::value))
    )
}

private fun getValidValue(nonUniqueValue: Int, values: List<Int>): Int {
    require(nonUniqueValue in values) { MSG_VALUE_NOT_PRESENT}
    var value = nonUniqueValue + 1
    while (value in values) value++
    return value
}

data class Pair(val id: Int, val value: Int)

const val MSG_PAIRS_EMPTY = "pairs not allowed to be empty"
const val MSG_VALUES_NEGATIVE = "values should be positive"
const val MSG_IDS_NOT_UNIQUE = "ids need to be unique"
const val MSG_VALUES_UNIQUE = "no value appears twice"
const val MSG_VALUE_NOT_PRESENT = "nonUniqueValue should be present in values"