package com.be2.test3.network

object validator {

    fun validateInput(url:String): Boolean {
        return !(url.isEmpty())

    }
}