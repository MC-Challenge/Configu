package net.challenge.configu.container.value

import net.challenge.configu.scanner.IValueScanner
import net.challenge.configu.value.Value

/**
 * Basic implementation of [IValueContainer].
 * The values are automatically read from the class using a [IValueScanner] and
 * entered into the list. After creating the container only the [load]
 * function must be executed.
 */
open abstract class ValueContainer(

    /**
     * Scanner to loading the values form this class.
     */
    private val scanner: IValueScanner

) : IValueContainer {

    /**
     * List of all values in this container.
     */
    override val values: MutableCollection<Value<*, *>> = mutableListOf()

    /**
     * Add all variables values of this class to the [values].
     */
    private fun autoValueLoad() {
        values += scanner.findValues(this)
    }

    /**
     * After creating the container it must be loaded.
     */
    override fun load() {
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