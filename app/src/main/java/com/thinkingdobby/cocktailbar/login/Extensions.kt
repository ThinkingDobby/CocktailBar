package com.thinkingdobby.cocktailbar.login

import java.security.DigestException
import java.security.MessageDigest

fun String.hashSHA256(): String {
    val hash: ByteArray
    try {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(this.toByteArray())
        hash = md.digest()
    } catch (e: CloneNotSupportedException) {
        throw DigestException("couldn't make digest of partial content");
    }

    return hash.bytesToHex()
}

fun ByteArray.bytesToHex(): String {
    val digits = "0123456789ABCDEF"
    val hexChars = CharArray(this.size * 2)
    for (i in this.indices) {
        val v = this[i].toInt() and 0xff
        hexChars[i * 2] = digits[v shr 4]
        hexChars[i * 2 + 1] = digits[v and 0xf]
    }
    return String(hexChars)
}