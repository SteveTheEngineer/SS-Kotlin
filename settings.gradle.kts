rootProject.name = "SS-Kotlin"

pluginManagement {
    val kotlinVersion: String by settings

    repositories {
        gradlePluginPortal()
        maven("https://mvn-public.steenesvc.cf/releases")
    }

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
    }
}
include("Bukkit")
include("Bungee")
