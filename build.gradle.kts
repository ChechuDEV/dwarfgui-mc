plugins {
    kotlin("jvm") version "1.8.20"
    application
    id("maven-publish")
}

group = "dev.chechu.mc"
version = "1.0-SNAPSHOT"
val javaVersion = 17

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(javaVersion)
}

publishing {
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }

    repositories {
        // Configure the repository where your library will be published
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/chechudev/dwarfgui-mc")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }

        }
    }
}