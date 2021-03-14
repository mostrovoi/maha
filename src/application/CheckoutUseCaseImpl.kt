package com.maha.application

import com.maha.domain.IdGrouper
import com.maha.domain.PriceCalculator
import com.maha.domain.WatchRepository

class CheckoutUseCaseImpl(private val watchRepository: WatchRepository) : CheckoutUseCase {

    override fun execute(listIds: List<Int>): Int {
        val idGrouped = IdGrouper.groupById(listIds)
        return idGrouped.map { watchId ->
            val watch = watchRepository.findByOrFail(watchId.key)
            if (watch.discount == null) {
                PriceCalculator.calculate(watch.price, watchId.value)
            } else {
                PriceCalculator.calculate(watch.price, watch.discount, watchId.value)
            }
        }.sum()
    }
}