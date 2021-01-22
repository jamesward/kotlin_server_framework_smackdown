plugins {
    kotlin("jvm") version "1.4.21" apply false
    kotlin("js") version "1.4.21" apply false
    kotlin("kapt") version "1.4.21" apply false
    kotlin("multiplatform") version "1.4.21" apply false
    kotlin("plugin.allopen") version "1.4.21" apply false

    // micronaut plugin has kapt issues if not added here
    id("io.micronaut.application") version "1.3.2" apply false
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        google()
    }
}