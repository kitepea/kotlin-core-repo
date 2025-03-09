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

// Subject (Concrete Subject)
class WeatherStation : Subject {
    val observers = mutableListOf<Observer>()
    private var temperature: Float = 0.0f
    private var humidity: Float = 0.0f

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer.update(temperature, humidity)
        }
    }

    // Update weather measurements
    fun setMeasurements(temp: Float, hum: Float) {
        this.temperature = temp
        this.humidity = hum
        notifyObservers() // Notify to all observers
    }
}

// Observer (Concrete Observer): Display weather data on phone
class PhoneDisplay(private val weatherStation: Subject) : Observer {
    init {
        weatherStation.registerObserver(this) // Register when creating object
        println("Constructor in PhoneDisplay is called")
    }

    override fun update(temperature: Float, humidity: Float) {
        println("📱 Phone Display - Temperature: $temperature°C, Humidity: $humidity%")
    }
}

// Observer (Concrete Observer): Display weather data on LED board
class LEDBoardDisplay(private val weatherStation: Subject) : Observer {
    init {
        weatherStation.registerObserver(this) // Register when creating object
    }

    override fun update(temperature: Float, humidity: Float) {
        println("💡 LED Board - Temperature: $temperature°C, Humidity: $humidity%")
    }
}

fun main() {
    val weatherStation = WeatherStation() // Create Subject

    val phoneDisplay = PhoneDisplay(weatherStation)  // Register observer
    val ledBoardDisplay = LEDBoardDisplay(weatherStation)  // Register observer

    println("🔄 Updating Weather Data...")
    weatherStation.setMeasurements(30.5f, 65f)  // Update weather -> notify observers

    println("\n🔄 Updating Weather Data Again...")
    weatherStation.setMeasurements(28.2f, 70f)  // Update weather
}

