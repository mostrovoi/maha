package application

import com.maha.application.CheckoutUseCase

class DummyCheckoutUseCase : CheckoutUseCase{
    override fun execute(listIds: List<Int>): Int {
        return 300
    }
}
