package com.maha.infrastructure

import com.maha.application.CheckoutUseCase

class CheckoutAdapter(private val checkoutUseCase: CheckoutUseCase) {

    fun execute(ids: List<String>): PriceDto {
        val numberIds = ids.map { it.toInt() }
        val total = checkoutUseCase.execute(numberIds)
        return PriceDto(price = total.toDouble() / 100)
    }
}