package com.example.bmicalculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    val viewModel: BMIViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btn_1Click(v: View) {
        hideKeyboard(v)
        try {
            viewModel.height = ev_1.text.toString().toDouble()
            viewModel.weight = ev_2.text.toString().toDouble()
        } catch (e : NumberFormatException) {
            Toast.makeText(applicationContext, R.string.NaN_toast, Toast.LENGTH_SHORT).show()
        }
        tv_5.setText(viewModel.bmiCalc().toString())
        when(viewModel.categorizeBMI()){
            -2 -> tv_6.setText(R.string.sev_uw)
            -1 -> tv_6.setText(R.string.li_uw)
            0 -> tv_6.setText(R.string.norm_w)
            1 -> tv_6.setText(R.string.li_ow)
            2 -> tv_6.setText(R.string.me_ow)
            3 -> tv_6.setText(R.string.sev_ow)
            4 -> tv_6.setText(R.string.tre_ow)
        }
    }

    fun hideKeyboard(v : View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}

