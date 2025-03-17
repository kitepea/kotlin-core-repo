import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val mutableStateFlow = MutableStateFlow<Int>(0)

    launch {
        mutableStateFlow.collect {
            println("Value $it")
        }
    }

    (1..10)
        .onEach {
            delay(1000)
            mutableStateFlow.emit(it)
        }
}
// 2:29:05
