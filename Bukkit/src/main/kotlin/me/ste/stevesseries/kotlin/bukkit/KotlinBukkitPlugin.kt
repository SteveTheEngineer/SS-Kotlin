package me.ste.stevesseries.kotlin.bukkit

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.InputStreamReader

class KotlinBukkitPlugin : JavaPlugin() {
    val stdlibVersion: String

    init {
        val stream = this.getResource("kotlin.yml")!!
        val reader = InputStreamReader(stream)

        val configuration = YamlConfiguration()
        configuration.load(reader)

        this.stdlibVersion = configuration.getString("version")!!
    }
}