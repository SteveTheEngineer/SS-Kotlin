plugins {
    id("kotlin")
    id("me.ste.stevesseries.bukkitgradle") version "1.0"
}

group = "me.ste.stevesseries.kotlin"
version = rootProject.version
description = "Provides the kotlin standard library."

dependencies {
    implementation("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
}

pluginDescription {
    name.set(rootProject.name)
    mainClass.set("me.ste.stevesseries.kotlin.bukkit.KotlinBukkitPlugin")
    apiVersion.set("1.14")
    authors.add("SteveTheEngineer")
}
