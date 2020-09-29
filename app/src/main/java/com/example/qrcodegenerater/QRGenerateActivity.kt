package com.example.qrcodegenerater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import com.example.qrcodegenerater.databinding.ActivityQRGenerateBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_q_r_generate.*
import java.lang.Exception

class QRGenerateActivity : AppCompatActivity() {

    lateinit var viewModel: QRGenerateModel
    lateinit var binding: ActivityQRGenerateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_generate)

        viewModel = QRGenerateModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_q_r_generate)
        binding.viewModel = viewModel

        val sharePref = PreferenceManager.getDefaultSharedPreferences(this)
        val taxId = sharePref.getString("tax_num", "0107555000392")
        val company = sharePref.getString("company", "อาคเนย์ประกันภัย")

        val intent = intent
        val ref1 = intent.getStringExtra("ref1")
        val ref2 = intent.getStringExtra("ref2")
        val amount = intent.getStringExtra("amount")
        val formatAmount = formatDecimal(amount)

        viewModel.setCompanyName(company)
        viewModel.setReference1(ref1)
        viewModel.setReference2(ref2)
        viewModel.setAmount(amount)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val billId = taxId
        val content = "|" + billId +"00\n$ref1\n$ref2\n$formatAmount"

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
        totalAmount = if (amount.contains(".")) {
            val dot = amount.indexOf('.')
            val newAmount = amount.substring(0, dot)
            val newDecimal = amount.substring(dot + 1)
            if (newDecimal.count() >= 2){
                "$newAmount$newDecimal"
            }else{
                "$newAmount$newDecimal" + "0"
            }
        }else{
            amount + "00"
        }

        return totalAmount
    }
}