package com.maha.domain

import com.maha.domain.Watch

interface WatchRepository {
    fun findByOrFail(id: Int): Watch
    fun addWatch(watch: Watch)
}
