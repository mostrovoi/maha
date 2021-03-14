val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project
val kluent_version: String by project
val kotlin_serialization: String by project
val kodein_version: String by project
val google_client_version: String by project

plugins {
    application
    kotlin("jvm") version "1.4.30"
}

group = "com.maha"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlin_serialization")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("org.kodein.di:kodein-di-generic-jvm:$kodein_version")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodein_version")
    implementation ("io.ktor:ktor-gson:$ktor_version")
    testImplementation("com.google.http-client:google-http-client:$google_client_version")
    testImplementation ("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.amshove.kluent:kluent:$kluent_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks {
    val java = "1.8"

    compileKotlin {
        kotlinOptions { jvmTarget = java }
        sourceCompatibility = java
    }
}