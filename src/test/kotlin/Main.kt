import net.challenge.configu.config.JsonConfig
import java.io.File

fun main() {
    //  ##############
    //      Example
    //  ##############

    var modules = listOf<Module>()

    modules += Module("FPS")
    modules += Module("CPS")
Int
    // Loading the modules
    modules.forEach { it.load() }

    // Using a json config to save the values
    val config = JsonConfig(File("config"), true)

    // Register all modules in the config
    modules.forEach { config.register(it) }

    // Loading the config
    config.load()

    // Saving the config
    config.save()
}