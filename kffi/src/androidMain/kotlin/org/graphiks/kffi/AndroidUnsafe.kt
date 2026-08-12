package org.graphiks.kffi

import java.lang.reflect.Field

internal object AndroidUnsafe {
    private val theUnsafe: sun.misc.Unsafe by lazy {
        val field: Field = sun.misc.Unsafe::class.java.getDeclaredField("theUnsafe")
        field.isAccessible = true
        field.get(null) as sun.misc.Unsafe
    }

    fun get(): sun.misc.Unsafe = theUnsafe
}
