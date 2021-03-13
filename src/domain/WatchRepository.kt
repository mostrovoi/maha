package com.maha.domain

interface WatchRepository {
    fun findByOrFail(id: Int): Watch
    fun addWatch(watch: Watch)
}
