package com.feylabs.bermatematika.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.ActivityMenuBinding
import com.feylabs.bermatematika.util.Glide

class MenuActivity : AppCompatActivity() {

    lateinit var viewBinding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        loadImage()

        viewBinding.apply {
            btnNotation.setOnClickListener {
             startActivity(Intent(applicationContext,
                 NotasiActivity::class.java))
            }

            btnClass7.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"1")
                moveActivity(intent)
            }

            btnClass8.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"2")
                moveActivity(intent)
            }

            btnClass9.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"3")
                moveActivity(intent)
            }

            btnClass10.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"4")
                moveActivity(intent)
            }

            btnClass11.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"5")
                moveActivity(intent)
            }

            btnClass12.setOnClickListener {
                val intent = Intent(applicationContext,FormulaActivity::class.java)
                intent.putExtra(FormulaActivity.CLASS_ID,"6")
                moveActivity(intent)
            }
        }
    }

    private fun loadImage() {
        Glide.loadImageFromLocal(this,R.drawable.rsz_class7,viewBinding.imgClass7)
        Glide.loadImageFromLocal(this,R.drawable.class8,viewBinding.imgClass8)
        Glide.loadImageFromLocal(this,R.drawable.class9,viewBinding.imgClass9)
        Glide.loadImageFromLocal(this,R.drawable.class10,viewBinding.imgClass10)
        Glide.loadImageFromLocal(this,R.drawable.class11,viewBinding.imgClass11)
        Glide.loadImageFromLocal(this,R.drawable.class12,viewBinding.imgClass12)
    }

    fun moveActivity(intent : Intent){
        startActivity(intent)
    }
}