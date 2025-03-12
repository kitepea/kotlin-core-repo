import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val counter = MutableSharedFlow<Int>(replay = 0, extraBufferCapacity = 10)

    launch {
        counter.collect { value ->
            println("Counter cập nhật: $value")
        }
    }

    counter.emit(1) // Không bị ghi đè
    counter.emit(2) // Không bị ghi đè
    counter.emit(3) // Không bị ghi đè
    counter.emit(4) // Không bị ghi đè

    delay(100)
}
