package domain

import com.maha.domain.IdGrouper
import org.amshove.kluent.`should be equal to`
import org.junit.Test

internal class IdGrouperTest {
    @Test
    fun `should return empty for empty list`() {
        val groupedIds = IdGrouper.groupById(emptyList())
        groupedIds `should be equal to` emptyMap()
    }

    @Test
    fun `should return grouped Ids for list`() {
        val listIds = listOf(1,3,2,1)

        val groupedIds = IdGrouper.groupById(listIds)

        val expectedMap = mapOf<Int,Int>(1 to 2, 2 to 1, 3 to 1)
        groupedIds `should be equal to` expectedMap
    }
}