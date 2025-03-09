import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun getNumbers(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(1000)
        emit(i) // Phát ra giá trị
    }
}

suspend fun main() {
    getNumbers().collect { println(it) } // Nhận dữ liệu
}
