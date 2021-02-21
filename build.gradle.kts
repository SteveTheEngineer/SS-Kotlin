import org.apache.tools.ant.filters.ReplaceTokens
import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.jetbrains.kotlin.jvm") version "1.4.30"
}

group = "me.ste.stevesseries"
version = System.getenv("BUILD_NUMBER")
if(version == "unspecified") {
    version = "0"
}

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

val kotlinVersion = "1.4.30"

dependencies {
    shadow("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("net.md-5:bungeecord-api:1.16-R0.5-SNAPSHOT")
    implementation("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
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
    from(sourceSets.main.get().allSource.srcDirs) {
        include("me/ste/stevesseries/kotlin/SSKotlin.kt")

        filter<ReplaceTokens>("tokens" to hashMapOf("version" to version))
    }
}