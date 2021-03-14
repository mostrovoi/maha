package infrastructure

import application.DummyCheckoutUseCase
import com.maha.infrastructure.CheckoutAdapter
import com.maha.infrastructure.PriceDto
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.junit.Test

internal class CheckoutAdapterTest {

    @Test
    fun `should parse well with right input values`() {
        val checkoutUseCase = DummyCheckoutUseCase()
        val checkoutAdapter = CheckoutAdapter(checkoutUseCase)
        val listIds = listOf("1", "2", "3", "4")
        val totalPrice = checkoutAdapter.execute(listIds)
        totalPrice `should be equal to` PriceDto(price = 300)
    }

    @Test
    fun `should parse well with empty values`() {
        val checkoutUseCase = DummyCheckoutUseCase()
        val checkoutAdapter = CheckoutAdapter(checkoutUseCase)
        val listIds = emptyList<String>()

        val totalPrice = checkoutAdapter.execute(listIds)
        totalPrice `should be equal to` PriceDto(price = 300)
    }

    @Test
    fun `should throw exception with wrong input values`() {
        val checkoutUseCase = DummyCheckoutUseCase()
        val checkoutAdapter = CheckoutAdapter(checkoutUseCase)
        val listIds = listOf("1", "a", "3", "4")

        invoking {
            checkoutAdapter.execute(listIds)
        } shouldThrow NumberFormatException::class
    }
}