package net.challenge.configu.value

/**
 * All [Value]s must be have this annotation.
 * This annotation contains the metadata of the [Value].
 */
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class VTag(val name: String, val description: String = "No-Description")