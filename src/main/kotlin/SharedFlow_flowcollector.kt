import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>()

    launch {
        sharedFlow.collect {
            println("Received: $it")
        }
    }
    launch {
        sharedFlow.collect {
            println("Received: $it")
        }
    }
    flow {
        repeat(10)
        {
            delay(800)
            emit(it)
        }
    }.collect(sharedFlow)
}