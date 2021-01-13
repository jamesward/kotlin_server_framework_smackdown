import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.4.2")
    implementation(project(":common"))

    implementation("io.micronaut:micronaut-runtime:2.2.3")

    kapt("io.micronaut:micronaut-inject-java:2.2.3")
    kaptTest("io.micronaut:micronaut-inject-java:2.2.3")

    // Server
    implementation("io.micronaut:micronaut-http-server-netty:2.2.3")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")

    // DB
    implementation("io.micronaut.sql:micronaut-jasync-sql:3.3.5")
    implementation("com.github.jasync-sql:jasync-postgresql:1.1.5")
    implementation("com.github.28Smiles:jasync-sql-extensions:0.4.2")
    testImplementation("org.testcontainers:postgresql:1.15.1")

    // UI
    implementation(project(":html-client"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        javaParameters = true
    }
}

application {
    mainClass.set("bars.MainKt")
}

kapt {
    arguments {
        arg("micronaut.processing.incremental", true)
    }
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-XX:TieredStopAtLevel=1", "-Dcom.sun.management.jmxremote")

    if (gradle.startParameter.isContinuous) {
        systemProperties = mapOf(
            "micronaut.io.watch.restart" to "true",
            "micronaut.io.watch.enabled" to "true",
            "micronaut.io.watch.paths" to "src"
        )
    }
}

tasks.register<JavaExec>("testRun") {
    dependsOn("testClasses")
    classpath = sourceSets["test"].runtimeClasspath
    main = "bars.MainKt"
}

tasks.replace("assemble").dependsOn("installDist")

tasks.register<DefaultTask>("stage") {
    dependsOn("installDist")
}

// todo: copy js-client artifact