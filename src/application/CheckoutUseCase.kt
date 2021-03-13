package com.maha.application

class CheckoutUseCase(private val watchRepository: WatchRepository) {

    fun execute(listIds: List<Int>): Int {
        var totalPrice = 0
        for(watchId in listIds) {
            val watch = watchRepository.findByOrFail(watchId)
            totalPrice += watch.price
        }
        return totalPrice
    }
}