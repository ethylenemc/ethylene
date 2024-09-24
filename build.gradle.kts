plugins {
    id("fabric-loom") version "1.7-SNAPSHOT"
}

val modVersion: String by extra
val minecraftVersion: String by extra
val loaderVersion: String by extra

version = modVersion
group = "dev.tonimatas"

base.archivesName.set("ethylene")

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    //modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
}

tasks.processResources {
    val properties = mapOf("version" to modVersion, "minecraftVersion" to minecraftVersion, "loaderVersion" to loaderVersion)
    inputs.properties(properties)
    
    filesMatching("fabric.mod.json") {
        expand(properties)
    }
}
