interface Animal {
    fun speak(): String
}

class Dog : Animal {
    override fun speak(): String = "Woof"
}

class Cat : Animal {
    override fun speak(): String = "Meow"
}

object AnimalFactory {
    fun createAnimal(type: String): Animal {
        return when (type.lowercase()) {
            "dog" -> Dog()
            "cat" -> Cat()
            else -> throw IllegalArgumentException("Unknown animal type")
        }
    }
}

fun main() {
    val myPet: Animal = AnimalFactory.createAnimal("dog")
    println(myPet.speak()) // Woof!

    val anotherPet: Animal = AnimalFactory.createAnimal("cat")
    println(anotherPet.speak()) // Meow!
}