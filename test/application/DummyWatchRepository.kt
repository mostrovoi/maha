package application

import com.maha.application.WatchRepository
import com.maha.domain.Watch
import com.maha.domain.WatchNotFoundException

class DummyWatchRepository : WatchRepository {
    private val watches = mutableMapOf<Int, Watch>()
    override fun findByOrFail(id: Int): Watch {
        val watch= watches[id]
        if(watch == null)
            throw WatchNotFoundException()
        else
            return watch
    }

    override fun addWatch(watch: Watch) {
        watches.put(watch.id, watch)
    }
}