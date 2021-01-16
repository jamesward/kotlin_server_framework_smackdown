plugins {
    id("io.gatling.gradle") version "3.5.0"
}

dependencies {
    gatlingImplementation("org.testcontainers:postgresql:1.15.1")
    gatlingRuntimeOnly("org.postgresql:postgresql:42.2.6")
    gatlingRuntimeOnly(project(":common"))
}

/*
gatling {
    logLevel = "DEBUG"
    logHttp = io.gatling.gradle.LogHttp.FAILURES
}
 */

tasks.withType<io.gatling.gradle.GatlingRunTask> {
    dependsOn(":micronaut-server:dockerBuild")
    dependsOn(":quarkus-server:dockerBuild")
    dependsOn(":springboot-server:bootBuildImage")
    dependsOn(":ktor-server:jibDockerBuild")
    outputs.upToDateWhen { false }
}