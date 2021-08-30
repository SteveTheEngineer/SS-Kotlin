import org.apache.tools.ant.filters.ReplaceTokens
import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.jetbrains.kotlin.jvm") version "1.4.30"
}

group = "me.ste.stevesseries"
version = "1.17.1-1.0.0-1.15.30"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

val kotlinVersion = "1.5.30"

dependencies {
    shadow("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("net.md-5:bungeecord-api:1.17-R0.1-SNAPSHOT")
    implementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

tasks.shadowJar {
    configurations = listOf(project.configurations.shadow.get())
    archiveClassifier.set(null as String?)
}

tasks.jar {
    enabled = false
    dependsOn("shadowJar")
}

tasks.processResources {
    from(sourceSets.main.get().resources.srcDirs) {
        include("bungee.yml")
        include("plugin.yml")
        include("kotlin.yml")

        filter<ReplaceTokens>("tokens" to hashMapOf("version" to version, "ktversion" to kotlinVersion))
    }
}