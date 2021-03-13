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
}