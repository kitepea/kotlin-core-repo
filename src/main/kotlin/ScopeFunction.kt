data class Person(var name: String, var age: Int)

fun main() {
    val person = Person("John", 25).apply {
        name = "Jane"
        age = 30
    }

    println(person)
}
// Output: Person(name=Jane, age=30)