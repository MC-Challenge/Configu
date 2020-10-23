package net.challenge.configu.value

import net.challenge.configu.events.ValueChangeEvent
import net.challenge.configu.events.ValueChangeListener

/**
 * A value contains a value.
 * This value has a name and a description which
 * is set with the help of a [VTag].
 */
open class Value<T, O : Value<T, O>> {

    open var value: T
        set(value) {
            val event = ValueChangeEvent(field, value)
            changeListener?.onChange(event)

            if (event.cancel) return

            field = event.after
        }

    /**
     * The default value.
     */
    val defaultValue: T

    /**
     * Name of the value
     */
    var name: String = "No-Name"

    /**
     * Description of the value
     */
    var description: String = "No-Description"

    var changeListener: ValueChangeListener<T>? = null

    constructor(value: T) {
        this.value = value
        this.defaultValue = value
    }

    /**
     * Set the change-listener of the value
     *
     * @param listener Listener to set.
     * @return this
     */
    fun setChangeListener(listener: ValueChangeListener<T>): O {
        changeListener = listener

        @Suppress("UNCHECKED_CAST")
        return this as O
    }

    /**
     * Set the value by a object.
     *
     * @param obj Value set to this object.
     */
    @Suppress("UNCHECKED_CAST")
    fun setObject(obj: Any) {
        this.value = (obj as T)
    }

    /**
     * Reset the value to default.
     */
    fun reset() {
        value = defaultValue
    }
}