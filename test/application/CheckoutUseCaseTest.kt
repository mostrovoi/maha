package application

import com.maha.application.CheckoutUseCase
import com.maha.domain.Watch
import com.maha.domain.WatchNotFoundException
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.junit.Test

internal class CheckoutUseCaseTest {

    @Test
    fun `should return 0 for empty list`() {
        val watchRepository = DummyWatchRepository()
        val checkoutUseCase = CheckoutUseCase(watchRepository)
        val listIds = emptyList<Int>()
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 0
    }

    @Test
    fun `should return the total price for a list containing one id`() {
        val watchRepository = DummyWatchRepository()
        val watch = Watch(id = 1, price = 20)
        watchRepository.addWatch(watch)
        val checkoutUseCase = CheckoutUseCase(watchRepository)

        val listIds = listOf(1)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 20
    }

    @Test
    fun `should return the total price for a list containing multiple ids`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = Watch(id = 1, price = 20)
        val secondWatch = Watch(id = 2, price = 30)
        watchRepository.addWatch(firstWatch)
        watchRepository.addWatch(secondWatch)

        val checkoutUseCase = CheckoutUseCase(watchRepository)

        val listIds = listOf(1, 2)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 50
    }

    @Test
    fun `should throw Exception if watch not found`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = Watch(id = 1, price = 20)
        val secondWatch = Watch(id = 2, price = 30)
        watchRepository.addWatch(firstWatch)
        watchRepository.addWatch(secondWatch)

        val checkoutUseCase = CheckoutUseCase(watchRepository)

        val listIds = listOf(1, 11)
        invoking {
            checkoutUseCase.execute(listIds)
        } shouldThrow WatchNotFoundException::class
    }


}