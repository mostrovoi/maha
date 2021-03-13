package com.maha.application

import com.maha.domain.IdGrouper
import com.maha.domain.PriceCalculator
import com.maha.domain.WatchRepository

class CheckoutUseCase(private val watchRepository: WatchRepository) {

    fun execute(listIds: List<Int>): Int {
        val idGrouped = IdGrouper.groupById(listIds)
        var totalPrice = 0
        for (watchId in idGrouped) {
            val watch = watchRepository.findByOrFail(watchId.key)
            if (watch.discount == null) {
                totalPrice += PriceCalculator.calculate(watch.price, watchId.value)
            } else {
                totalPrice += PriceCalculator.calculate(watch.price, watch.discount, watchId.value)
            }
        }
        return totalPrice
    }
}