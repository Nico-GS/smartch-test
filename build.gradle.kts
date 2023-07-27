val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.2"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"
}

// KSP - To use generated sources
sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}

group = "com.app"
version = "0.0.1"
application {
    mainClass.set("com.app.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.0.2")
    implementation("io.ktor:ktor-server-netty:2.0.2")
    ksp ("io.insert-koin:koin-ksp-compiler:1.2.2")
    implementation("io.ktor:ktor-serialization:2.0.2")
    implementation("io.insert-koin:koin-ktor:3.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.insert-koin:koin-logger-slf4j:3.1.5")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")


    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    testImplementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")

    implementation ("io.insert-koin:koin-core:3.4.2")


    // https://mvnrepository.com/artifact/io.insert-koin/koin-annotations
    runtimeOnly("io.insert-koin:koin-annotations:1.2.2")
// https://mvnrepository.com/artifact/io.insert-koin/koin-ksp-compiler
    implementation("io.insert-koin:koin-ksp-compiler:1.2.2")





}
