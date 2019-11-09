package com.isaquecoelho.simplemovieapp.util

import java.security.MessageDigest

class StringUtils {

    fun hashToSha256(text: String): String{
        val textBytes = text.toByteArray()
        val textmessageDigest = MessageDigest.getInstance("SHA-256")
        val textDigest = textmessageDigest.digest(textBytes)
        return textDigest.fold("", { str, it -> str + "%02x".format(it) }).toUpperCase()
    }
}