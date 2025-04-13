import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val myFlow = flow {
        println("Flow bắt đầu chạy")
        emit(1)
        emit(2)
        emit(3)
    }

    delay(1000) // Chờ một chút nhưng Flow vẫn chưa chạy
    println("Chưa gọi collect nên Flow chưa chạy")

    myFlow.collect { println("Nhận: $it") }
}
