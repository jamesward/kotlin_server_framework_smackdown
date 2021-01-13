plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.webjars:bootstrap:4.5.3")
    api("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

/*
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        javaParameters = true
    }
}
 */