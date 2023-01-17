import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    id("kotlin")
    id("com.github.SteveTheEngineer.SS-BukkitGradle") version "1.4"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    `maven-publish`
}

group = "me.ste.stevesseries.kotlin"
archivesName.set("SS-Kotlin-Compat")
version = rootProject.version
description = "Compatibility version."

dependencies {
    implementation("org.spigotmc:spigot-api:1.14-R0.1-SNAPSHOT")
}

pluginDescription {
    name.set(rootProject.name)
    mainClass.set("me.ste.stevesseries.kotlin.bukkit.KotlinBukkitPlugin")
    apiVersion.set("1.14")
    authors.add("SteveTheEngineer")
}

publishing {
    publications {
        create<MavenPublication>("plugin") {
            artifactId = "kotlin-compat"

            from(project.components["java"])
        }
    }
}
