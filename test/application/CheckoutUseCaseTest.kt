package application

import com.maha.application.CheckoutUseCase
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class CheckoutUseCaseTest {

    @Test
    fun `should return 0 for empty list`() {
        val checkoutUseCase = CheckoutUseCase()
        val listIds = emptyList<Int>()
        val totalPrice = checkoutUseCase.execute(listIds)
        totalPrice `should be equal to` 0
    }

}