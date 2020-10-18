package net.challenge.configu.config

import net.challenge.configu.container.ISaveContainer

/**
 * In a config you can register [ISaveContainer].
 * The config then manages the saving and loading
 * of the registered [ISaveContainer].
 */
interface IConfig {

    /**
     * A list of all registered [ISaveContainer].
     */
    var containers: List<ISaveContainer>

    /**
     * Register a [ISaveContainer].
     *
     * @param container The container to be registered.
     */
    fun register(vararg container: ISaveContainer) {
        containers = containers + container
    }

    /**
     * Save all registered [ISaveContainer].
     */
    fun save()

    /**
     * Load all registered [ISaveContainer].
     */
    fun load()
}