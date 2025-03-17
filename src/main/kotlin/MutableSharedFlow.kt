import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlin.time.Duration.Companion.seconds

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)
    val mutableStateFlow = MutableSharedFlow<Int>(
        replay = 1
    )

    mutableStateFlow.onEach {
        println("[1] Received: $it")
    }.launchIn(scope)

    scope.launch {
        delay(500)
        repeat(10) {
            mutableStateFlow.emit(it)
        }
        println("Emit 10 items done")
    }

    delay(2.seconds)

    mutableStateFlow
        .onStart {
            println("[2] Start collecting...")
        }
        .onEach {
            println("[2] Received: $it")
        }
        .launchIn(scope)

    delay(100)

    repeat(10) {
        mutableStateFlow.emit(it + 10)
    }

    delay(30.seconds)
    scope.cancel()
    println("Done")
}

/*
[1] Received: 0
[1] Received: 1
[1] Received: 2
[1] Received: 3
[1] Received: 4
[1] Received: 5
[1] Received: 6
[1] Received: 7
[1] Received: 8
[1] Received: 9
Emit 10 items done
[2] Start collecting...
[2] Received: 9
[1] Received: 10
[1] Received: 11
[2] Received: 10
[2] Received: 11
[2] Received: 12
[1] Received: 12
[1] Received: 13
[2] Received: 13
[2] Received: 14
[1] Received: 14
[2] Received: 15
[1] Received: 15
[2] Received: 16
[1] Received: 16
[1] Received: 17
[1] Received: 18
[2] Received: 17
[2] Received: 18
[2] Received: 19
[1] Received: 19*/
