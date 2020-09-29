package com.example.qrcodegenerater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_version.*

class VersionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version)

        val pInfo = applicationContext.packageManager.getPackageInfo(packageName, 0)
        val stringVersion = resources.getString(R.string.version, pInfo.versionName)
        version_textview.text = stringVersion

        toolbar.setNavigationOnClickListener {
            finish()
        }

    }
}