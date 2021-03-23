package com.feylabs.bermatematika.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feylabs.bermatematika.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    lateinit var viewBinding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.apply {
            btnNotation.setOnClickListener {
             startActivity(Intent(applicationContext,
                 NotasiActivity::class.java))
            }

            btnClass7.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"1")
                startActivity(intent)
            }
        }


    }
}