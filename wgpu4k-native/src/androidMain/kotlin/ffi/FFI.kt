package ffi

import java.lang.foreign.ValueLayout

val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN
val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE
val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT
val C_INT: ValueLayout = ValueLayout.JAVA_INT
val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG
val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT
val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE
val C_POINTER: ValueLayout = ValueLayout.ADDRESS
val C_LONG: ValueLayout = ValueLayout.JAVA_LONG