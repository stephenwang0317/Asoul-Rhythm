package com.example.bellavoice

import androidx.compose.runtime.sourceInformation
import com.example.bellavoice.myutils.ShortUrltoLong
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val str:String = ShortUrltoLong("https://b23.tv/8ClJ5VU")
        println(str)
    }
}