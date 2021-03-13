package com.maha.domain

data class Watch(val id: Int, val name: String, val price: Int, val discount: Discount? = null)