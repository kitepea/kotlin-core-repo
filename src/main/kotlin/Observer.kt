// Interface/class that observes Subject & needs updating when Subject changes
interface Observer {
    fun update(temperature: Float, humidity: Float)
}

// Object that contains a list of observers and notifies them when a change occurs
interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}