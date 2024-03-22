plugins {
    kotlin("jvm") version "1.9.22"
}

group = "dev.isxander"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://repo.spongepowered.org/maven/")
}

dependencies {
    implementation(libs.fabric.loader)
    implementation(libs.tiny.mappings.parser)
    implementation(libs.mixin) {
        exclude(group = "com.google.code.gson")
        exclude(group = "com.google.guava")
    }
    implementation(libs.guava)
    implementation(libs.tiny.remapper)
    implementation(libs.access.widener)
    implementation(libs.bundles.asm)
}

kotlin {
    jvmToolchain(21)
}

val runModded by tasks.registering(JavaExec::class) {
    dependsOn(tasks.jar)

    group = "run"
    description = "Runs the launcher with fabric loader"
    classpath(files(tasks.jar.get().archiveFile, sourceSets.main.get().runtimeClasspath) - sourceSets.main.get().output)
    mainClass = "dev.isxander.modlauncher.loader.MainKt"
    jvmArgs?.add("-Dfabric.development=true")
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(21))
    })
}