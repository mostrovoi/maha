package application

import com.maha.application.CheckoutUseCaseImpl
import com.maha.domain.Discount
import com.maha.domain.WatchNotFoundException
import feature.WatchStub
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.junit.Test

internal class CheckoutUseCaseTest {

    @Test
    fun `should return 0 for empty list`() {
        val watchRepository = DummyWatchRepository()
        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)
        val listIds = emptyList<Int>()
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 0
    }

    @Test
    fun `should return the total price for a list containing one id`() {
        val watchRepository = DummyWatchRepository()
        val watch = WatchStub.create(id = 1, price = 20)
        watchRepository.addWatch(watch)
        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)

        val listIds = listOf(1)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 20
    }

    @Test
    fun `should return the total price for a list containing multiple ids`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = WatchStub.create(id = 1, price = 20)
        val secondWatch = WatchStub.create(id = 2, price = 30)
        watchRepository.addWatch(firstWatch)
        watchRepository.addWatch(secondWatch)

        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)

        val listIds = listOf(1, 2)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 50
    }

    @Test
    fun `should throw Exception if watch not found`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = WatchStub.create(id = 1, price = 20)
        val secondWatch = WatchStub.create(id = 2, price = 30)
        watchRepository.addWatch(firstWatch)
        watchRepository.addWatch(secondWatch)

        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)

        val listIds = listOf(1, 11)
        invoking {
            checkoutUseCase.execute(listIds)
        } shouldThrow WatchNotFoundException::class
    }

    @Test
    fun `should return discounted price for a watch`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = WatchStub.create(id = 1, price = 20, discount = Discount(3, 200))
        watchRepository.addWatch(firstWatch)

        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)

        val listIds = listOf(1, 1, 1)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 200
    }

    @Test
    fun `should return combined price (discounted + regular price) for a series of same watches`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = WatchStub.create(id = 1, price = 20, discount = Discount(3, 200))
        watchRepository.addWatch(firstWatch)

        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)

        val listIds = listOf(1, 1, 1, 1)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 220
    }

    @Test
    fun `should return combined price (discounted + regular price) for a series of watches`() {
        val watchRepository = DummyWatchRepository()
        val firstWatch = WatchStub.create(id = 1, price = 20, discount = Discount(3, 200))
        val secondWatch = WatchStub.create(id = 2, price = 20, discount = Discount(2, 100))

        watchRepository.addWatch(firstWatch)
        watchRepository.addWatch(secondWatch)

        val checkoutUseCase = CheckoutUseCaseImpl(watchRepository)

        val listIds = listOf(1, 1, 1, 1, 2, 2, 2)
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 340
    }
}