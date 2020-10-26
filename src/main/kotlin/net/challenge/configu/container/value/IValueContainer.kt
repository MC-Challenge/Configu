package net.challenge.configu.container.value

import net.challenge.configu.config.IConfig
import net.challenge.configu.container.ISaveContainer
import net.challenge.configu.value.Value

/**
 * With this container you can store a list of [Value]s in the [IConfig].
 */
interface IValueContainer : ISaveContainer {

    /**
     * Collection of all values in this container.
     */
    val values: MutableCollection<Value<*, *>>

    /**
     * After creating the container it must be loaded.
     */
    fun load()
}