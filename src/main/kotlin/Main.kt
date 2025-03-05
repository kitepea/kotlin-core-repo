import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    // Create an Observable that emits a sequence of integers
    val observable = Observable.create<Int> { emitter ->
        try {
            for (i in 1..5) {
                if (emitter.isDisposed) return@create
                emitter.onNext(i) // Emit next value
                Thread.sleep(1000) // Simulate delay
            }
            emitter.onComplete() // Complete the emission
        } catch (e: Exception) {
            emitter.onError(e) // Emit an error if any occurs
        }
    }

    // Subscribe to the Observable
    observable
        .subscribeOn(Schedulers.io()) // Specify the thread to perform subscription
        .observeOn(Schedulers.single()) // Specify the thread to observe the emissions
        .subscribe(
            { item -> println("Received: $item") }, // onNext
            { error -> println("Error: $error") }, // onError
            { println("Completed") } // onComplete
        )

    // Keep the main thread alive for a while to see the output
    Thread.sleep(6000)
}