package com.example.qrcodegenerater

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class FormViewModel: ViewModel() {

    val message1 = ObservableField("")
    val message2 = ObservableField("")
    val isEnabled = ObservableField(false)

    fun onMessage1TextChanged(s: CharSequence,start: Int,before : Int, count :Int){
        message1.set(s.toString())
        checkEnabled()
    }

    fun onMessage2TextChanged(s: CharSequence,start: Int,before : Int, count :Int){
        message2.set(s.toString())
        checkEnabled()
    }

    private fun checkEnabled(){
        if(message1.get()!!.isNotEmpty() && message2.get()!!.isNotEmpty()){
            isEnabled.set(true)
        }else{
            isEnabled.set(false)
        }
    }
}