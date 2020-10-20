package net.challenge.configu.value

/**
 * A value contains a value.
 * This value has a name and a description which
 * is set with the help of a [VTag].
 */
open class Value<T>(open var value: T) {

    /**
     * Name of the value
     */
    var name: String = "No-Name"
        private set

    /**
     * Description of the value
     */
    var description: String = "No-Description"
        private set

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
     * Set info from the tag to the value
     *
     * @param tag Use this tag to update
     */
    fun setTagInfo(tag: VTag) {
        name = tag.name
        description = tag.description
    }
}