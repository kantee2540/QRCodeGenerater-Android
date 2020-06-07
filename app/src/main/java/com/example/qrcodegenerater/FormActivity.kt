package com.example.qrcodegenerater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.qrcodegenerater.databinding.ActivityFormBinding
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    lateinit var viewModel :FormViewModel
    lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        viewModel = FormViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)
        binding.viewmodel = viewModel

        submit_button.setOnClickListener {

            val mes1 = ref1_edittext.text.toString()
            val mes2 = ref2_edittext.text.toString()

            val intent = Intent(applicationContext, QRGenerateActivity::class.java)

            intent.putExtra("ref1", mes1)
            intent.putExtra("ref2", mes2)

            startActivity(intent)
        }

    }
}