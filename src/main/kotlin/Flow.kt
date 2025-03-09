import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun foo(): Flow<Int> = flow {
    for (i in 3 downTo -3) {
        emit(i)
        delay(1000)
    }
}

fun main(): Unit = runBlocking {
    println("Starting collection with launchIn")
    foo()
        .onEach { value -> println("Value ${3 / value}") }
        .catch { println("Exception ${it.message}") }
        .launchIn(this)
    println("Finished collection with launchIn")
}