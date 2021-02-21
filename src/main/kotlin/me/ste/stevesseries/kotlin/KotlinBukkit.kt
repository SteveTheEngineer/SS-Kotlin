package me.ste.stevesseries.kotlin

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.InputStreamReader

class KotlinBukkit: JavaPlugin() {
    val stdlibVersion: String
    init {
        val configuration = YamlConfiguration()
        configuration.load(InputStreamReader(this.getResource("kotlin.yml")!!))
        this.stdlibVersion = configuration.getString("version")!!
    }
}