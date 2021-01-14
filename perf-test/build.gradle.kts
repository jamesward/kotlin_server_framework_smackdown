plugins {
    id("io.gatling.gradle") version "3.5.0"
}

dependencies {
    implementation("org.testcontainers:postgresql:1.15.1")
}

tasks.withType<io.gatling.gradle.GatlingRunTask> {
    dependsOn(":micronaut-server:jibDockerBuild")
    dependsOn(":quarkus-server:dockerBuild")
}