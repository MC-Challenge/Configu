package net.challenge.configu.value

/**
 * A value contains a value.
 * This value has a name and a description which
 * is set with the help of a [VTag].
 */
open class Value<T> {

    open var value: T

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

    constructor(value: T) {
        this.value = value
        this.defaultValue = value
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