import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("io.micronaut.application")
}

micronaut {
    version.set("2.2.3")
    runtime("netty")
    processing {
        incremental(true)
        annotations("bars.*")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.4.2")
    implementation(project(":common"))

    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")

    // Server
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

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

tasks.register<JavaExec>("testRun") {
    dependsOn("testClasses")
    classpath = sourceSets["test"].runtimeClasspath
    main = "bars.MainKt"
}

// todo: copy js-client artifact