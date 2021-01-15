import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("com.google.cloud.tools.jib") version "2.7.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":common"))
    implementation(project(":html-client"))

    implementation("io.ktor:ktor-server-core:1.5.0")
    implementation("io.ktor:ktor-server-cio:1.5.0")
    implementation("io.ktor:ktor-jackson:1.5.0")

    implementation("io.ktor:ktor-html-builder:1.5.0")
    implementation("io.ktor:ktor-webjars:1.5.0")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

    implementation("io.micronaut.sql:micronaut-jasync-sql:3.3.5")
    implementation("com.github.jasync-sql:jasync-postgresql:1.1.5")
    implementation("com.github.28Smiles:jasync-sql-extensions:0.4.2")
    testImplementation("org.testcontainers:postgresql:1.15.1")
}

application {
    mainClass.set("bars.MainKt")
}

tasks.register<JavaExec>("testRun") {
    dependsOn("testClasses")
    classpath = sourceSets["test"].runtimeClasspath
    main = "bars.TestMainKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

// todo: copy js-client artifact