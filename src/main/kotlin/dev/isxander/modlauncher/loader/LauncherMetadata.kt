package dev.isxander.modlauncher.loader

import net.fabricmc.loader.api.Version
import net.fabricmc.loader.api.metadata.*
import net.fabricmc.loader.impl.metadata.AbstractModMetadata.TYPE_BUILTIN
import java.util.*

class LauncherMetadata(version: String) : ModMetadata {
    private val version = Version.parse(version)

    override fun getType(): String = TYPE_BUILTIN

    override fun getId(): String = "modlauncher"

    override fun getProvides(): Collection<String> = listOf()

    override fun getVersion(): Version = version

    override fun getEnvironment(): ModEnvironment = ModEnvironment.CLIENT

    override fun getDependencies(): Collection<ModDependency> = listOf()

    override fun getName(): String = "ModLauncher"

    override fun getDescription(): String = "Launch mods"

    override fun getAuthors(): Collection<Person> = listOf()

    override fun getContributors(): Collection<Person> = listOf()

    override fun getContact(): ContactInformation? = null

    override fun getLicense(): Collection<String> = listOf()

    override fun getIconPath(size: Int): Optional<String> = Optional.empty()

    override fun containsCustomValue(key: String?): Boolean = false

    override fun getCustomValue(key: String?): CustomValue? = null

    override fun getCustomValues(): Map<String, CustomValue> = mapOf()

    @Deprecated("Deprecated in Java", ReplaceWith("containsCustomValue"))
    override fun containsCustomElement(key: String?): Boolean = false
}