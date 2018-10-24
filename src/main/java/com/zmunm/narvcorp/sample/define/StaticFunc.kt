package com.zmunm.narvcorp.sample.define

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.experimental.and
import kotlin.math.pow

object StaticFunc {
    fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive + 1) - start) +  start
    fun ClosedFloatingPointRange<Double>.random() =
            Random().nextDouble()*((endInclusive)-start) +  start
    fun Double.round(digit:Int) =
            Math.round(this*10.0.pow(digit))/10.0.pow(digit)

    fun <T> List<T>.use(separater:Int,block: List<T>.() -> Unit):List<T> {
        take(separater).block()
        return this.drop(separater)
    }

    fun ByteArray.toHex(
            separator:String = ""
    ) = joinToString(separator) { String.format("%02x", it and 0xff.toByte()) }

    fun sha256encode(password:String) = MessageDigest.getInstance("SHA-256")
            .digest(password.toByteArray()).fold("") { str, it->str + "%02x".format(it)}

    val offset = SimpleDateFormat("Z", Locale.ROOT)
            .format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).time)
            .toInt()/100.0f
}
