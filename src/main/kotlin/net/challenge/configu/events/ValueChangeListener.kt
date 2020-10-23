package net.challenge.configu.events

import net.challenge.configu.value.Value

/**
 * This listener is used with [Value].
 * It is called when a value changes.
 */
interface ValueChangeListener<T> {

    /**
     * This event is executed when a value changes.
     *
     * @param event This contains all important information about the change in value
     */
    fun onChange(event: ValueChangeEvent<T>)
}