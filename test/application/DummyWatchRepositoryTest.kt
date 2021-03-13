package application

import com.maha.domain.WatchNotFoundException
import com.maha.infrastructure.InMemoryWatchRepository
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.junit.Test

internal class InMemoryWatchRepositoryTest {

    @Test
    fun `should retrieve entries for database`() {
        val repository = InMemoryWatchRepository()

        1 `should be equal to` repository.findByOrFail(1).id
        2 `should be equal to` repository.findByOrFail(2).id
        invoking {
            repository.findByOrFail(6)
        } shouldThrow WatchNotFoundException::class
    }
}

