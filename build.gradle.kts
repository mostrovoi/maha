val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project
val kluent_version: String by project
val kotlin_serialization: String by project
val exposed_version: String by project
val h2_version: String by project
val hikari_version: String by project

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
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlin_serialization")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation ("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation ("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation ("com.h2database:h2:$h2_version")
    implementation ("com.zaxxer:HikariCP:$hikari_version")


    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.amshove.kluent:kluent:$kluent_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
