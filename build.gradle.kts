import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    val kotlinVersion = "1.9.25"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "3.4.0-M3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("org.jetbrains.kotlinx.kover") version "0.8.3"
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kover {
    reports {
        filters {
            excludes {
                classes(
                    "fr.xiang.tinyurl.configuration.*",
                    "fr.xiang.tinyurl.LoggingKt",
                    "fr.xiang.tinyurl.TinyUrlApplication",
                    "fr.xiang.tinyurl.TinyUrlApplicationKt",
                )
            }
        }
    }
}
