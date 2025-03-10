interface DataCallback {
    fun onSuccess(data: String)
    fun onError(error: Throwable)
}

fun fetchData(callback: DataCallback) {
    // Giả lập một tác vụ bất đồng bộ
    Thread {
        try {
            Thread.sleep(1000) // Giả lập độ trễ mạng
            callback.onSuccess("Dữ liệu tải về thành công!")
        } catch (e: Exception) {
            callback.onError(e)
        }
    }.start()
}

fun main() {
    fetchData(object : DataCallback {
        override fun onSuccess(data: String) {
            println("Nhận dữ liệu: $data")
        }

        override fun onError(error: Throwable) {
            println("Lỗi: ${error.message}")
        }
    })
}
