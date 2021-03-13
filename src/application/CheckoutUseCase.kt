package com.maha.application

interface CheckoutUseCase {
    fun execute(listIds: List<Int>): Int
}