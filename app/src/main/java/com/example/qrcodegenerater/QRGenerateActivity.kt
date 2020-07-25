package com.example.qrcodegenerater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        ref1_text.text = message1
        ref2_text.text = message2

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val billId = "0107555000392"
        val content = "|" + billId +"00\n$message1\n$message2\n0"
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
}