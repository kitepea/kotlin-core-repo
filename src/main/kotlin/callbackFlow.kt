import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

interface WebSocketCallback {
    fun receiveData(msg: Int)
}

class ServiceTest {
    private var callback: WebSocketCallback? = null

    fun registerCallback(callback: WebSocketCallback) {
        this.callback = callback
        println(">>> registerCallback")
    }

    fun unregisterCallback() {
        this.callback = null
        println(">>> unregisterCallback")
    }

    fun execute(msg: Int) {
        callback?.receiveData(msg)
    }
}

private fun createWebSocketCallback(): Flow<Int> = callbackFlow {
    val serviceTest = ServiceTest()
    val callbackTest = object : WebSocketCallback {
        override fun receiveData(msg: Int) {
            trySend(msg)
        }
    }

    serviceTest.registerCallback(callbackTest)

    serviceTest.execute(1)
    serviceTest.execute(2)
    serviceTest.execute(3)
    serviceTest.execute(4)

    awaitClose { serviceTest.unregisterCallback() }
}

fun main(): Unit = runBlocking {
    launch {
        createWebSocketCallback()
            .cancellable()
            .collect {
                println(">>> collect: $it")
                if (it == 3)
                    cancel()
            }
    }
}