package com.maha.domain

data class Discount(val total: Int, val discountPrice: Int) {
    init {
        require(total > 0)
        require(discountPrice > 0)
    }
}
