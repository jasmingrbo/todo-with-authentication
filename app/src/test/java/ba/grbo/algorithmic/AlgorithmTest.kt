package ba.grbo.algorithmic

import org.junit.Assert
import org.junit.Test


internal class AlgorithmTest {
    @Test
    fun getPairSeriesOfTests() {
        // Given
        val arrayOfPairs = arrayOf(
            arrayOf(
                Pair(id = 1, value = 3),
                Pair(id = 2, value = 7),
                Pair(id = 3, value = 3),
                Pair(id = 4, value = 1),
                Pair(id = 5, value = 4)
            ),
            arrayOf(
                Pair(id = 1, value = 1),
                Pair(id = 2, value = 1),
                Pair(id = 3, value = 4),
                Pair(id = 4, value = 7),
                Pair(id = 5, value = 2),
                Pair(id = 6, value = 8),
                Pair(id = 7, value = 12),
                Pair(id = 8, value = 9),
                Pair(id = 9, value = 6)
            ),
            arrayOf(
                Pair(id = 1, value = 1),
                Pair(id = 2, value = 1),
                Pair(id = 3, value = 1),
            ),
            arrayOf(
                Pair(id = 1, value = 1),
                Pair(id = 2, value = 1),
                Pair(id = 3, value = 2),
                Pair(id = 4, value = 2),
                Pair(id = 5, value = 3),
                Pair(id = 6, value = 3),
                Pair(id = 7, value = 3),
                Pair(id = 8, value = 4),
                Pair(id = 9, value = 5),
                Pair(id = 10, value = 6),
                Pair(id = 11, value = 6),
            ),
            arrayOf(
                Pair(id = 1, value = 1),
                Pair(id = 2, value = 1),
                Pair(id = 3, value = 3),
                Pair(id = 4, value = 5),
                Pair(id = 5, value = 7),
                Pair(id = 6, value = 12),
                Pair(id = 7, value = 6),
                Pair(id = 8, value = 7),
                Pair(id = 9, value = 2),
                Pair(id = 10, value = 6),
                Pair(id = 11, value = 4),
                Pair(id = 12, value = 9),
                Pair(id = 13, value = 11),
            ),
            arrayOf(
                Pair(id = 1, value = 1),
                Pair(id = 2, value = 1),
                Pair(id = 3, value = 1),
                Pair(id = 4, value = 1)
            ),
            arrayOf(
                Pair(id = 1, value = 4),
                Pair(id = 2, value = 3),
                Pair(id = 3, value = 2),
                Pair(id = 4, value = 1),
                Pair(id = 5, value = 1),
                Pair(id = 6, value = 4)
            ),
            arrayOf(
                Pair(id = 1, value = 0),
                Pair(id = 2, value = 1),
                Pair(id = 3, value = 0),
                Pair(id = 4, value = 1),
                Pair(id = 5, value = 0),
                Pair(id = 6, value = 1),
                Pair(id = 7, value = 0),
                Pair(id = 8, value = 1),
                Pair(id = 9, value = 0),
                Pair(id = 10, value = 1),
            ),
            arrayOf(
                Pair(id = 1, value = 7),
                Pair(id = 2, value = 7),
                Pair(id = 3, value = 2),
            ),
            arrayOf(
                Pair(id = 1, value = 3),
                Pair(id = 2, value = 3),
            ),
            arrayOf(
                Pair(id = 1, value = 0),
                Pair(id = 2, value = 10),
                Pair(id = 3, value = 20),
                Pair(id = 4, value = 30),
                Pair(id = 5, value = 40),
                Pair(id = 6, value = 10),
                Pair(id = 7, value = 60),
            )
        )

        val solutions = listOf(
            Pair(id = 6, value = 5),
            Pair(id = 10, value = 3),
            Pair(id = 4, value = 2),
            Pair(id = 12, value = 7),
            Pair(id = 14, value = 8),
            Pair(id = 5, value = 2),
            Pair(id = 7, value = 5),
            Pair(id = 11, value = 2),
            Pair(id = 4, value = 8),
            Pair(id = 3, value = 4),
            Pair(id = 8, value = 11)
        )

        // When
        val computedPairs = arrayOfPairs.map { pairs -> getPair(pairs) }

        // Then
        Assert.assertEquals(solutions, computedPairs)
    }

    @Test
    fun getPairGivenEmptyArrayThrowIllegalArgumentException() {
        // Given
        val pairs = arrayOf<Pair>()

        // When && Then
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) { getPair(pairs)}
        Assert.assertEquals(MSG_PAIRS_EMPTY, exception.message)
    }

    @Test
    fun getPairGivenArrayWithNegativeValuesThrowIllegalArgumentException() {
        // Given
        val pairs = arrayOf(
            Pair(id = 1, value = 15),
            Pair(id = 2, value = 11),
            Pair(id = 3, value = 4),
            Pair(id = 4, value = 13),
            Pair(id = 5, value = 9),
            Pair(id = 6, value = -11),
        )

        // When && Then
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) { getPair(pairs)}
        Assert.assertEquals(MSG_VALUES_NEGATIVE, exception.message)
    }

    @Test
    fun getPairGivenArrayWithNonUniqueIdsThrowIllegalArgumentException() {
        // Given
        val pairs = arrayOf(
            Pair(id = 1, value = 4),
            Pair(id = 2, value = 7),
            Pair(id = 3, value = 2),
            Pair(id = 1, value = 2),
            Pair(id = 4, value = 8)
        )

        // When && Then
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) { getPair(pairs)}
        Assert.assertEquals(MSG_IDS_NOT_UNIQUE, exception.message)
    }

    @Test
    fun getPairGivenArrayWithUniqueValuesThrowIllegalArgumentException() {
        // Given
        val pairs = arrayOf(
            Pair(id = 1, value = 1),
            Pair(id = 2, value = 3),
            Pair(id = 3, value = 2),
            Pair(id = 4, value = 7),
            Pair(id = 5, value = 5),
            Pair(id = 6, value = 8),
            Pair(id = 7, value = 12),
            Pair(id = 8, value = 9),
            Pair(id = 9, value = 6)
        )

        // When && Then
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) { getPair(pairs)}
        Assert.assertEquals(MSG_VALUES_UNIQUE, exception.message)
    }
}