import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    val counter = MutableStateFlow(0) // Giá trị ban đầu là 0

    launch {
        counter.collect { value ->
            println("Counter cập nhật: $value")
        }
    }

//    delay(100)
    counter.value = 1  // Phát ra giá trị mới
//    delay(100)
    counter.value = 2  // Phát ra giá trị mới
    counter.value = 2  // Không phát ra vì giá trị không thay đổi
//    delay(100)
    counter.value = 3  // Phát ra giá trị mới

    delay(100)
}
