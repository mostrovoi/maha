package domain

import com.maha.domain.Discount
import com.maha.domain.PriceCalculator
import org.amshove.kluent.`should be equal to`
import org.junit.Test

internal class PriceCalculatorTest {

    @Test
    fun `should return the price if the discount criteria is not met`() {
        val totalPrice = PriceCalculator.calculate(200, Discount(3, 100), 3)
        totalPrice `should be equal to` 600
    }
}