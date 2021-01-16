plugins {
    kotlin("jvm") version "1.4.21" apply false
    kotlin("js") version "1.4.21" apply false
    kotlin("kapt") version "1.4.21" apply false
    kotlin("multiplatform") version "1.4.21" apply false
    kotlin("plugin.allopen") version "1.4.21" apply false
    id("io.micronaut.application") version "1.2.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
        google()
    }
}