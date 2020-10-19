package net.challenge.configu.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.challenge.configu.container.ISaveContainer
import java.io.File
import java.io.FileReader
import java.io.FileWriter

/**
 * Implementation of [IConfig].
 * This config saving [ISaveContainer] as json files.
 */
class JsonConfig(

    /**
     * Under this path these config files are stored.
     */
    private val path: File,

    /**
     * Configures Gson to output Json that fits in a page for pretty printing.
     */
    prettyPrinting: Boolean

) : IConfig {

    private val gson: Gson =
        if (prettyPrinting)
            GsonBuilder().setPrettyPrinting().create()
        else
            Gson()

    override var containers: List<ISaveContainer> = listOf()

    init {

        // Create the path if the path doesn't exist
        if (!path.exists())
            path.mkdirs()
    }

    override fun save() {
        containers.forEach {
            val json = gson.toJson(it.getObjectToSave())

            val fileName = it.getFileName().replace(" ", "_")
            val file = File(path, "${fileName}.json")

            val myWriter = FileWriter(file)
            myWriter.write(json)
            myWriter.close()
        }
    }

    override fun load() {
        containers.forEach {
            val fileName = it.getFileName().replace(" ", "_")
            val file = File(path, "${fileName}.json")

            if (!file.exists()) return

            val obj = gson.fromJson(FileReader(file), Any::class.java)

            it.setLoadObject(obj)
        }
    }
}