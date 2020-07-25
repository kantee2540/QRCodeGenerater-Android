package com.example.qrcodegenerater

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class FormViewModel: ViewModel() {

    val message1 = ObservableField("")
    val message2 = ObservableField("")
    val amount = ObservableField("")
    val isEnabled = ObservableField(false)
    val isShowClear1 = ObservableField(false)
    val isShowClear2 = ObservableField(false)
    val isShowClearAmount = ObservableField(false)

    fun onMessage1TextChanged(s: CharSequence,start: Int,before : Int, count :Int){
        message1.set(s.toString())
        if(message1.get()!!.isNotEmpty()){
           isShowClear1.set(true)
        }else{
            isShowClear1.set(false)
        }
        checkEnabled()
    }

    fun onMessage2TextChanged(s: CharSequence,start: Int,before : Int, count :Int) {
        message2.set(s.toString())
        if(message2.get()!!.isNotEmpty()){
            isShowClear2.set(true)
        }else{
            isShowClear2.set(false)
        }
        checkEnabled()
    }

    fun onAmountTextChanged(s: CharSequence,start: Int,before : Int, count :Int){
        amount.set(s.toString())
        if (amount.get()!!.isNotEmpty()){
            isShowClearAmount.set(true)
        }else{
            isShowClearAmount.set(false)
        }
    }

    fun onClickClear1(){
        message1.set("")
    }

    fun onClickClear2(){
        message2.set("")
    }

    fun onClickClearAmount(){
        amount.set("")
    }

    private fun checkEnabled(){
        if(message1.get()!!.isNotEmpty() && message2.get()!!.isNotEmpty()){
            isEnabled.set(true)
        }else{
            isEnabled.set(false)
        }
    }
}