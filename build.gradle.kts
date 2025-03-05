plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}