fun orderFood(foodName: String, callBack: (String) -> Unit) {
    println("Ordering: $foodName...")

    Thread.sleep(2000)

    val result = "$foodName ready!"

    callBack(result)
}

fun main() {
    orderFood("Pizza") { response ->
        println("Result: $response")
    }
}
