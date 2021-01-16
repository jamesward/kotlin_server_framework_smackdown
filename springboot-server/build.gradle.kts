import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    maven(uri("https://repo.spring.io/milestone"))
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
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("io.r2dbc:r2dbc-postgresql")
    implementation("org.postgresql:postgresql")
    testImplementation("org.testcontainers:postgresql:1.15.1")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.springframework.experimental:spring-graalvm-native:0.8.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    dependsOn("testClasses")
    classpath = configurations["developmentOnly"] + sourceSets["test"].runtimeClasspath
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootBuildImage> {
    if (project.hasProperty("native")) {
        val args = setOf(
            "-Dspring.spel.ignore=true",
            "-Dspring.native.remove-yaml-support=true",
            "--no-fallback"
        )
        builder = "paketobuildpacks/builder:tiny"
        environment = mapOf(
            "BP_BOOT_NATIVE_IMAGE" to "1",
            "BP_BOOT_NATIVE_IMAGE_BUILD_ARGUMENTS" to args.joinToString(" ")
        )
    }
}

application {
    mainClass.set("bars.MainKt")
}

// todo: copy js-client artifact