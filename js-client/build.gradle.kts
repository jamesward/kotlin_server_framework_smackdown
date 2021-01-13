plugins {
    kotlin("js")
}

kotlin {
    js {
        browser {
            runTask {
                devServer = devServer?.copy(port = 8081, open = false)
            }
        }
        binaries.executable()
    }
    sourceSets["main"].dependencies {
        implementation(kotlin("stdlib-js"))
        implementation(project(":common"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.4.2")
        implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
    }
}