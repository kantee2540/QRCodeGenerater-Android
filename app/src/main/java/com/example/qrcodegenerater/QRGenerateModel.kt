package com.example.qrcodegenerater

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_q_r_generate.*

class QRGenerateModel: ViewModel() {
    val companyName = ObservableField("อาคเนย์ประกันภัย")
    val reference1 = ObservableField("99999999999")
    val reference2 = ObservableField("99999999999")
    val amount = ObservableField("0.00")

    fun setCompanyName(name: String?){
        companyName.set(name)
    }

    fun setReference1(ref: String?){
        reference1.set(ref)
    }

    fun setReference2(ref: String?){
        reference2.set(ref)
    }

    fun setAmount(amountNum: String?){
        if (!amountNum!!.contains(".") && amountNum != ""){
            amount.set("$amountNum.00")
        }else if(amountNum == ""){
            amount.set("0.00")
        }else{
            amount.set(amountNum)
        }
    }

}