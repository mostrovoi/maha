package com.maha.domain

class PriceCalculator {
    companion object {
        fun calculate(price: Int, discount: Discount, amount: Int): Int {
            return price*amount
        }
    }
}
