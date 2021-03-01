package com.be2.test3.network

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class validatorTest{

    @Test
    fun whenvalid(){

        val url = "http://www.rigidohub.xyz/browser/api/"
        val reslut = validator.validateInput(url)
        assertThat(reslut).isEqualTo(true)

    }

    @Test
    fun wheninvalid(){
        val url = "http://www.google.com"
        val reslut = validator.validateInput(url)
        assertThat(reslut).isEqualTo(false)

    }

}