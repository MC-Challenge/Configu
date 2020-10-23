package net.challenge.configu.events

import net.challenge.configu.value.Value

/**
 * This class contains all information
 * and functions for the [ValueChangeListener].
 */
class ValueChangeEvent<T>(

    /**
     * The current value of the [Value.value].
     */
    val before: T,

    /**
     * The value that the [Value.value] should take.
     */
    var after: T

) {

    /**
     * If canceled is true the change process should be aborted.
     */
    var cancel = false
        private set

    /**
     * Cancel the change process.
     */
    fun cancel() {
        cancel = true
    }
}