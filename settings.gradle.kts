rootProject.name = "SS-Kotlin"

pluginManagement {
    val kotlinVersion: String by settings

    repositories {
        gradlePluginPortal()
        maven("https://jitpack.io")
    }

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
    }
}
include("Bukkit")
include("Bungee")
include("BukkitCompat")
