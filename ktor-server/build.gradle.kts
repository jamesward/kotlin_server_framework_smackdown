import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("com.google.cloud.tools.jib") version "2.7.1"
    id("com.palantir.graal") version "0.7.2"
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

jib {
    container {
        mainClass = application.mainClass.get()
    }
}

graal {
    outputName("ktor-server")
    graalVersion("20.3.0")
    mainClass(application.mainClass.get())
    javaVersion("8")
    option("--verbose")
    option("--no-server")
    option("--no-fallback")
    option("-H:+ReportExceptionStackTraces")
    option("-H:+PrintClassInitialization")
    option("-H:ConfigurationFileDirectories=src/graal")
}

// todo: copy js-client artifact