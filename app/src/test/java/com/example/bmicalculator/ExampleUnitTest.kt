package com.example.bmicalculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val test = BMIViewModel()

    @Test
    fun addition_isCorrect() {
        test.height=1.7
        test.weight=70.0
        print(test.bmiCalc())
        assertTrue(24.2<test.bmiCalc() && test.bmiCalc()<24.25)
        assertEquals(0,test.categorizeBMI())
    }
}