package com.feylabs.bermatematika.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.ActivityFormulaExampleBinding

class FormulaExampleActivity : AppCompatActivity() {

    val binding by lazy { ActivityFormulaExampleBinding.inflate(layoutInflater) }

    companion object {
        const val CONSO = "consoo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.webView.apply {
            val html = intent.getStringExtra(CONSO)
            settings.javaScriptEnabled = true
            if (html != null) {
                loadData(html, "text/html;charset=utf-8", "UTF-8")
            }
        }
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}