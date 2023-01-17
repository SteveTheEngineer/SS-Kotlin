plugins {
    id("kotlin")
    id("com.github.SteveTheEngineer.SS-BukkitGradle") version "1.5"
}

group = "me.ste.stevesseries.kotlin"
version = rootProject.version
description = "Provides the kotlin standard library."

dependencies {
    implementation("org.spigotmc:spigot-api:1.14-R0.1-SNAPSHOT")
}

pluginDescription {
    name.set(rootProject.name)
    mainClass.set("me.ste.stevesseries.kotlin.bukkit.KotlinBukkitPlugin")
    apiVersion.set("1.14")
    authors.add("SteveTheEngineer")
}
