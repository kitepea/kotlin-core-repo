import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EventBus<Event> {
    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events.asSharedFlow()

    suspend fun post(event: Event) {
        _events.emit(event)
    }
}

fun main(): Unit = runBlocking {
    val eventBus = EventBus<String>()
    val job1 = launch {
        eventBus.events.collect { event ->
            println("[1] Received event: $event")
        }
    }
    val job2 = launch {
        eventBus.events.collect { event ->
            println("[2] Received event: $event")
        }
    }
    delay(1000)

    eventBus.post("Hello")
    eventBus.post("World")

    delay(1000)

    job1.cancelAndJoin()
    job2.cancelAndJoin()
}