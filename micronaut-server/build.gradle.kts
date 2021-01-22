import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("io.micronaut.application")
}

micronaut {
    version.set("2.3.0")
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
    implementation(project(":zeko-db"))

    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")

    // Server
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    // DB
    implementation("io.micronaut.sql:micronaut-jasync-sql")
    implementation("com.github.jasync-sql:jasync-postgresql:1.1.5")
    testImplementation("org.testcontainers:postgresql:1.15.1")
    testRuntimeOnly("org.postgresql:postgresql:42.2.6") // todo: transitive

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
    systemProperty("micronaut.environments", "test")
    systemProperty("micronaut.server.port", "8080")
}

tasks.withType<io.micronaut.gradle.graalvm.NativeImageTask> {
    args("--verbose")
    args("-H:ResourceConfigurationResources=META-INF/native-image/micronaut-server/resource-config.json")
    args("-H:IncludeResources='.*/output_xml.properties\$'")
}

tasks.named<com.bmuschko.gradle.docker.tasks.image.DockerBuildImage>("dockerBuildNative") {
    images.set(listOf(project.name + "-native"))
}

// todo: copy js-client artifact