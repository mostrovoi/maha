package com.maha.application

import com.maha.domain.IdGrouper
import com.maha.domain.WatchRepository

class CheckoutUseCase(private val watchRepository: WatchRepository) {

    fun execute(listIds: List<Int>): Int {
        val idGrouped = IdGrouper.groupById(listIds)
        var totalPrice = 0
        for(watchId in listIds) {
            val watch = watchRepository.findByOrFail(watchId)
            totalPrice += watch.price
        }
        return totalPrice
    }
}