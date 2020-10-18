package net.challenge.configu.container

import net.challenge.configu.config.IConfig

/**
 * A container contains an object.
 * This object can be saved and loaded using a [IConfig].
 */
interface ISaveContainer {

    /**
     * Returns the object that should be saved.
     *
     * @return Object to save.
     */
    fun getObjectToSave(): Any

    /**
     * Set the object. By default this method is
     * used to set the loaded object to the container
     * after loading.
     *
     * @param obj The loaded object.
     */
    fun setLoadObject(obj: Any)

    /**
     * Get the name under which this container should be save.
     *
     * @return Name of the file.
     */
    fun getFileName(): String
}