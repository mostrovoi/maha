package com.maha.domain

class PriceCalculator {
    companion object {

        fun calculate(price: Int, amount: Int): Int {
            return amount * price
        }

        fun calculate(price: Int, discount: Discount, amount: Int): Int {
            val discountTimesApplied = amount.div(discount.total)
            val remainderTimesApplied = amount.rem(discount.total)
            return (discount.discountPrice * discountTimesApplied) + (remainderTimesApplied * price)
        }
    }
}
