package me.ste.stevesseries.kotlin

import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration

class KotlinBungee: Plugin() {
    val stdlibVersion: String = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(this.getResourceAsStream("kotlin.yml")).getString("version")
}