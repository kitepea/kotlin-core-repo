import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val mutableStateFlow = MutableStateFlow<Int>(0)
    val readonlyFlow = mutableStateFlow.asStateFlow()

    launch {
        readonlyFlow.collect {
            println("Current value: ${mutableStateFlow.value}")
            println("Current value: ${readonlyFlow.value}")
            println("Value $it \n")
        }
    }

    mutableStateFlow.emit(0)
    delay(100)
    mutableStateFlow.emit(1)
    delay(100)
    mutableStateFlow.emit(0)
    // 2:29:05
}