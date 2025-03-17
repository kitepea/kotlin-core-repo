import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking {
    // transform operator
    /*    flowOf(1, 2, 3, 4, 5)
            .transform {
                emit(it * 2)
            }.collect(::println)*/

    // distinctUntilChanged operator
    /*    flowOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)
            .distinctUntilChanged()
            .collect(::println)*/

    // flatMap operator
    (1..3)
        .asFlow()
        .onEach { delay(100) }
        .flatMapLatest { createFlow(it) }
        .collect(::println)
}

fun createFlow(value: Int) = flow {
    emit("$value: First")
    delay(500)
    emit("$value: Second")
}