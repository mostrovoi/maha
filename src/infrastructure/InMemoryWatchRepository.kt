package com.maha.infrastructure

import com.maha.domain.Discount
import com.maha.domain.Watch
import com.maha.domain.WatchNotFoundException
import com.maha.domain.WatchRepository

class InMemoryWatchRepository : WatchRepository {
    private val watches = mutableMapOf<Int, Watch>()

    init {
        this.addWatch(
            Watch(
                id = 1,
                name = "Rolex",
                price = 100,
                discount = Discount(total = 3, discountPrice = 200)
            )
        )
        this.addWatch(
            Watch(
                id = 2,
                name = "Michael Kors",
                price = 80,
                discount = Discount(total = 2, discountPrice = 120)
            )
        )
        this.addWatch(Watch(id = 3, name = "Swatch", price = 50))
        this.addWatch(Watch(id = 4, name = "Casio", price = 30))

    }

    override fun findByOrFail(id: Int): Watch {
        val watch = watches[id]
        if (watch == null)
            throw WatchNotFoundException()
        else
            return watch
    }

    override fun addWatch(watch: Watch) {
        watches[watch.id] = watch
    }
}