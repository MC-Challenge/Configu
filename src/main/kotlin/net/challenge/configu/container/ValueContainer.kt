package net.challenge.configu.container

import net.challenge.configu.config.IConfig
import net.challenge.configu.scanner.IValueScanner
import net.challenge.configu.value.Value

/**
 * With this container you can store a list of [Value]s in the [IConfig].
 * The values are automatically read from the class using a [IValueScanner] and
 * entered into the list. After creating the container only the [load]
 * function must be executed.
 */
open abstract class ValueContainer(

    /**
     * Scanner to loading the values form this class.
     */
    private val scanner: IValueScanner

) : ISaveContainer {

    /**
     * List of all values in this container.
     */
    var values: List<Value<*, *>> = listOf()
        protected set

    /**
     * Add all variables values of this class to the [values].
     */
    private fun autoValueLoad() {
        values += scanner.findValues(this)
    }

    /**
     * After creating the container it must be loaded.
     */
    fun load() {
        autoValueLoad()
    }

    override fun getObjectToSave(): Any {
        val map: MutableMap<String, Any?> = mutableMapOf()

        values.forEach {
            map += Pair(it.name, it.value)
        }

        return map
    }

    override fun setLoadObject(obj: Any) {
        if (obj !is Map<*, *>)
            return

        this.values.forEach {
            obj[it.name]?.let { obj -> it.setObject(obj) }
        }
    }
}