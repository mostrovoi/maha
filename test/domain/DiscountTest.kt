package domain

import com.maha.domain.Discount
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldThrow
import org.junit.Test

internal class DiscountTest {

    @Test
    fun `should create Discount object for valid values`() {
        val discount = Discount(total = 1, discountPrice = 20)
        discount.total `should be equal to` 1
        discount.discountPrice `should be equal to` 20
    }

    @Test
    fun `should throw IllegalArgumentException for non valid total`() {
        invoking {
            Discount(total = 0, discountPrice = 20)
        } shouldThrow IllegalArgumentException::class
    }

    @Test
    fun `should throw IllegalArgumentException for non valid discountPrice`() {
        invoking {
            Discount(total = 1, discountPrice = -20)
        } shouldThrow IllegalArgumentException::class
    }
}