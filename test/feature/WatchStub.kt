package feature

import com.maha.domain.Discount
import com.maha.domain.Watch

class WatchStub() {
    companion object {
        fun create(
            id: Int = Math.random().toInt(),
            price: Int = Math.random().toInt(),
            name: String = "dummy sample",
            discount: Discount? = null
        ): Watch {
            return Watch(
                id = id,
                price = price,
                name = name,
                discount = discount
            )
        }
    }
}

