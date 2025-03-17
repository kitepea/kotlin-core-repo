import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val mutableStateFlow = MutableStateFlow(0)
    val stateFlow: StateFlow<Int> = mutableStateFlow

// Collect values from stateFlow
    launch {
        stateFlow.collect { value ->
            println("Collector 1 received: $value")
        }
    }

// Collect values from stateFlow
    launch {
        stateFlow.collect { value ->
            println("Collector 2 received: $value")
        }
    }

// Update the state
    launch {
        repeat(3) { i ->
            mutableStateFlow.value = i
        }
    }
}