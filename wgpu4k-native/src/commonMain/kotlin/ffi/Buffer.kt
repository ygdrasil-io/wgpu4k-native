package ffi

expect class Buffer(handler: NativeAddress, size: ULong) {

    val size: ULong
    val handler: NativeAddress

    fun readLong(): Long
    fun writeLong(value: Long)
    fun readFloat(): Float
    fun writeFloat(value: Float)
    fun readInt(): Int
    fun writeInt(value: Int)
    fun readPointer(): NativeAddress
    fun writePointer(value: NativeAddress)

}