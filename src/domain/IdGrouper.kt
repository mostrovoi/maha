package com.maha.domain

class IdGrouper {
    companion object {
        fun groupById(watchIds: List<Int>): Map<Int, Int> {
            return watchIds.groupingBy { it }.eachCount()
        }
    }
}
