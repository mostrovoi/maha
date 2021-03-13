package com.maha.application

class CheckoutUseCase(private val watchRepository: WatchRepository) {

    fun execute(listIds: List<Int>): Int {
        for(watchId in listIds) {
            val watch = watchRepository.findByOrFail(watchId)
            return watch.price
        }
        return 0
    }
}