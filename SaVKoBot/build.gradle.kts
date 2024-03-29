import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"

    id("com.github.johnrengelman.shadow") version "5.1.0"
}

group = "ru.byprogminer.sasearch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.vk.api", "sdk", "+")
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "ru.byprogminer.sasearch.MainKt"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
