package com.example.bmicalculator

import androidx.lifecycle.ViewModel

class BMIViewModel : ViewModel() {

    var weight : Double = 0.0
    var height : Double = 0.0
    var result : Double = 0.0

    fun bmiCalc() {
        result = weight / Math.pow(height, 2.0)
    }

    fun categorizeBMI() : Int {
       if(result < 16) return -3
       else if(result>=16 && result < 17) return -2
       else if(result>=17 && result < 18.5) return -1
       else if(result>=15.5 && result < 25) return 0
       else if(result>=25 && result < 30) return 1
       else if(result>=30 && result < 35) return 2
       else if(result>=35 && result < 40) return 3
       else return 4
    }

}