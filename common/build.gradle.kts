plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm().compilations.all {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            javaParameters = true
        }
    }

    js {
        browser()
    }
}