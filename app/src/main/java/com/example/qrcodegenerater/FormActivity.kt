package com.example.qrcodegenerater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
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

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val company = sharedPreferences.getString("company", "อาคเนย์ประกันภัย")
        val taxId = sharedPreferences.getString("tax_num", "0107555000392")

        val editor = sharedPreferences.edit()
        editor.putString("company", company)
        editor.putString("tax_num", taxId)

        editor.apply()

        app_title.text = company

        submit_button.setOnClickListener {

            val mes1 = ref1_edittext.text.toString()
            val mes2 = ref2_edittext.text.toString()
            val amount = amount_edittext.text.toString()

            val intent = Intent(applicationContext, QRGenerateActivity::class.java)

            intent.putExtra("ref1", mes1)
            intent.putExtra("ref2", mes2)
            intent.putExtra("amount", amount)

            startActivity(intent)
        }

        setting_button.setOnClickListener {
            val intent = Intent(applicationContext, SettingsActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val company = sharedPreferences.getString("company", "อาคเนย์ประกันภัย")
        app_title.text = company
    }
}