package me.ste.stevesseries.kotlin.bungee

import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration

class KotlinBungeePlugin: Plugin() {
    val stdlibVersion: String

    init {
        val provider = ConfigurationProvider.getProvider(YamlConfiguration::class.java)

        val stream = this.getResourceAsStream("kotlin.yml")
        val config = provider.load(stream)

        this.stdlibVersion = config.getString("version")
    }
}