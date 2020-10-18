package net.challenge.configu.value

/**
 * TODO Doc
 */
open class Value<T>(var value: T) {

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