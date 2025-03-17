import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.seconds

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val mutableStateFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 8,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    mutableStateFlow
        .onEach {
            println("[1] Received: $it")
        }
        .launchIn(scope)

    mutableStateFlow
        .onEach {
            println("[2] Received: $it")
            delay(5.seconds)
        }
        .launchIn(scope)

    repeat(30) {
        println(">>>> Sending: $it")
        println(mutableStateFlow.tryEmit(it))
        println("<<<< Sent: $it")
    }

    delay(30.seconds)
    scope.cancel()
    println("Done")
}