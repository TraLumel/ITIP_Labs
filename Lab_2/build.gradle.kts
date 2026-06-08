import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.nio.charset.StandardCharsets

plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("org.example.Main")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("ch.qos.logback:logback-classic:1.5.12")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    manifest {
        attributes(Pair("Main-Class", "org.example.Main"))
    }
}

abstract class PrintInfoTask : DefaultTask() {
    @TaskAction
    fun printInfo() {
        println("======================================")
        println("Это моя первая пользовательская задача!")
        println("Проект: ${project.name}")
        println("Версия Gradle: ${project.gradle.gradleVersion}")
        println("======================================")
    }
}

tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom"
    description = "Выводит информацию о проекте"
}

abstract class GenerateBuildInfoTask : DefaultTask() {
    @TaskAction
    fun generate() {
        val resourcesDir = project.file("src/main/resources")
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs()
        }
        val passportFile = File(resourcesDir, "build-passport.properties")

        val user = System.getenv("USERNAME") ?: System.getenv("USER") ?: "Unknown"
        val os = System.getProperty("os.name")
        val javaVersion = System.getProperty("java.version")
        val dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        passportFile.writeText("""
            build.user=$user
            build.os=$os
            build.java.version=$javaVersion
            build.time=$dateTime
            build.message=Hello from Custom Gradle Task!
        """.trimIndent(), StandardCharsets.UTF_8)
    }
}

tasks.register<GenerateBuildInfoTask>("generateBuildInfo") {
    group = "Custom"
    description = "Создает файл build-passport.properties перед сборкой"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.named("processResources") {
    dependsOn(tasks.named("generateBuildInfo"))
}

