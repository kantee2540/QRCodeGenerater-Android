package com.example.qrcodegenerater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_q_r_generate.*
import java.lang.Exception

class QRGenerateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_generate)

        val intent = intent
        val message1 = intent.getStringExtra("ref1")
        val message2 = intent.getStringExtra("ref2")
        val amount = intent.getStringExtra("amount")
        val formatAmount = formatDecimal(amount)

        ref1_text.text = message1
        ref2_text.text = message2
        amount_text.text = amount

        if (!amount!!.contains(".") && amount != ""){
            amount_text.text = "$amount.00"
        }else if(amount == ""){
            amount_text.text = "0.00"
        }else{
            amount_text.text = amount
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val sharePref = PreferenceManager.getDefaultSharedPreferences(this)
        val taxId = sharePref.getString("tax_num", "0107555000392")
        val editor = sharePref.edit()
        editor.putString("tax_num", taxId)
        editor.apply()

        val billId = taxId
        val content = "|" + billId +"00\n$message1\n$message2\n$formatAmount"
        generateQR(content)
        generateBarcode(content)
    }

    private fun generateQR(message: String) {
        try{
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(message, BarcodeFormat.QR_CODE, 300, 300)
            qrImage.setImageBitmap(bitmap)

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun generateBarcode(message: String){
        try{
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(message, BarcodeFormat.CODE_128, 200, 50)
            barcode.setImageBitmap(bitmap)

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun formatDecimal(amount: String): String{
        var totalAmount = ""
        if (amount.contains(".")) {
            val dot = amount.indexOf('.')
            val newAmount = amount.substring(0, dot)
            val newDecimal = amount.substring(dot + 1)
            if (newDecimal.count() >= 2){
                totalAmount = "$newAmount$newDecimal"
            }else{
                totalAmount = "$newAmount$newDecimal" + "0"
            }
        }else{
            totalAmount = amount + "00"
        }

        return totalAmount
    }
}