plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

group = "io.inkwellmc.restart"
version = properties["plugin_version"].toString()


apply {
    plugin("java")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
}

tasks.jar {
    archiveClassifier = "dev"
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveFileName = "Restart-$version.jar"
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}