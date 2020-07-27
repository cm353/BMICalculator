package com.example.bmicalculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BMIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(BMIViewModel::class.java)
        parseRadioGroup()
        if(!rb_centimeter.isChecked && !rb_meter.isChecked)
            rb_centimeter.setChecked(true)

    }

    fun btn_1Click(v: View) {
        hideKeyboard(v)
        parseUserInput()
        calcBMI()
        updateUI()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(viewModel.result != 0.0) {
            updateUI()
        }
    }

    fun parseUserInput() {
        try {
            viewModel.height = ev_1.text.toString().toDouble()
            viewModel.weight = ev_2.text.toString().toDouble()
        } catch (e : NumberFormatException) {
            Toast.makeText(applicationContext, R.string.NaN_toast, Toast.LENGTH_SHORT).show()
        }
    }

    fun parseRadioGroup() {
        rg_height.setOnCheckedChangeListener({ radioGroup: RadioGroup, i: Int ->
            if (rb_meter.isChecked)
                viewModel.unitWeightKg = true
            else if (rb_centimeter.isChecked)
                viewModel.unitWeightKg = false
        })
    }

    fun calcBMI(){
        viewModel.bmiCalc()
    }

    fun updateUI() {
        tv_5.setText(viewModel.result.toString())
        when(viewModel.categorizeBMI()){
            -3 -> tv_6.setText(R.string.sev_uw)
            -2 -> tv_6.setText(R.string.me_uw)
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
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
    }

}

