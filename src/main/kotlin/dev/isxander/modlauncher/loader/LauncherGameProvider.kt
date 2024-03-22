package dev.isxander.modlauncher.loader

import net.fabricmc.loader.impl.game.GameProvider
import net.fabricmc.loader.impl.game.GameProvider.BuiltinMod
import net.fabricmc.loader.impl.game.patch.GameTransformer
import net.fabricmc.loader.impl.launch.FabricLauncher
import net.fabricmc.loader.impl.util.Arguments
import java.io.File
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.Path

class LauncherGameProvider : GameProvider {
    private val classpath = mutableListOf<Path>()
    private val transformer = GameTransformer()
    private val arguments = Arguments()

    override fun getGameId(): String = "mod_launcher"

    override fun getGameName(): String = "ModLauncher"

    override fun getRawGameVersion(): String = "1.0"

    override fun getNormalizedGameVersion(): String = "1.0"

    override fun getBuiltinMods(): Collection<BuiltinMod> = listOf(
        BuiltinMod(classpath, LauncherMetadata(normalizedGameVersion))
    )

    override fun getEntrypoint(): String =
        "dev.isxander.modlauncher.main.MainKt"

    override fun getLaunchDirectory(): Path = Path.of(".").toRealPath()

    override fun isObfuscated(): Boolean = false

    override fun requiresUrlClassLoader(): Boolean = false

    override fun isEnabled(): Boolean = true

    override fun locateGame(launcher: FabricLauncher, args: Array<out String>): Boolean {
        arguments.parse(args)

        val codeSource = LauncherGameProvider::class.java.protectionDomain.codeSource
        val codePath = Paths.get(codeSource.location.toURI())

        classpath.add(codePath)

        return true
    }

    override fun initialize(launcher: FabricLauncher) {
        val parentClassPath = System.getProperty("java.class.path")
            .split(File.pathSeparator)
            .stream()
            .peek { println(it) }
            .map { Path(it).toRealPath() }
            .filter { !classpath.contains(it) }.toList()

        launcher.setValidParentClassPath(parentClassPath)
        entrypointTransformer.locateEntrypoints(launcher, classpath)
    }

    override fun getEntrypointTransformer(): GameTransformer = transformer

    override fun unlockClassPath(launcher: FabricLauncher) {
        classpath.forEach { launcher.addToClassPath(it) }
    }

    override fun launch(loader: ClassLoader) {
        val target = loader.loadClass(entrypoint)
        val invoker = MethodHandles.lookup().findStatic(target, "main", MethodType.methodType(Void.TYPE, Array<String>::class.java))

        invoker.invokeExact(arguments.toArray())
    }

    override fun getArguments(): Arguments = arguments

    override fun getLaunchArguments(sanitize: Boolean): Array<String> = arguments.toArray()
}