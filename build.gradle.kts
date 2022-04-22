import org.apache.tools.ant.filters.ReplaceTokens
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
}

val kotlinVersion: String by ext
val kotlinxSerializationVersion: String by ext
val kotlinxCoroutinesVersion: String by ext

group = "me.ste.stevesseries.kotlin"
version = kotlinVersion

allprojects {
    apply<KotlinPluginWrapper>()

    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }
}

dependencies {
    shadow(kotlin("stdlib"))
    shadow(kotlin("reflect"))
    shadow(kotlin("serialization"))

    shadow("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$kotlinxSerializationVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-cbor:$kotlinxSerializationVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-properties:$kotlinxSerializationVersion")
    shadow("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$kotlinxSerializationVersion")

    shadow("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
}

tasks.shadowJar {
    dependsOn(*subprojects.map { it.tasks.jar.get() }.toTypedArray())

    for (subproject in subprojects) {
        from(subproject.configurations.archives.get().allArtifacts.files.map {
            zipTree(it)
        })
    }

    configurations = listOf(project.configurations.shadow.get())
    archiveClassifier.set(null as String?)
}

tasks.jar {
    enabled = false
    dependsOn("shadowJar")
}

tasks.processResources {
    inputs.property("kotlinVersion", kotlinVersion)
    inputs.property("version", version)

    from(sourceSets.main.get().resources.srcDirs) {
        include("bungee.yml")
        include("kotlin.yml")

        duplicatesStrategy = DuplicatesStrategy.INCLUDE

        filter<ReplaceTokens>("tokens" to hashMapOf("version" to version, "ktversion" to kotlinVersion))
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/SteveTheEngineer/SS-Kotlin")
            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("USERNAME"))?.toString()
                password = (project.findProperty("gpr.key") ?: System.getenv("TOKEN"))?.toString()
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            from(components.getByName("java"))
        }
    }
}
