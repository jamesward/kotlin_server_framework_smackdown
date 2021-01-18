import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.4.2")

    implementation("io.zeko:zeko-data-mapper:1.6.2")
    implementation("io.zeko:zeko-sql-builder:1.2.2")

    api(project(":common"))

    implementation("com.github.jasync-sql:jasync-postgresql:1.1.5")

    testImplementation("io.kotest:kotest-runner-junit5:4.3.2")
    testImplementation("io.kotest:kotest-extensions-testcontainers:4.3.2")
    testImplementation("org.testcontainers:postgresql:1.15.1")
    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.30")
    testRuntimeOnly("org.postgresql:postgresql:42.2.6")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
        events(STARTED, PASSED, SKIPPED, FAILED)
    }
}