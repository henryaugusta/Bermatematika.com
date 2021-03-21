package com.feylabs.bermatematika.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun String.makeLongToast(){
        Toast.makeText(this@BaseActivity,this,Toast.LENGTH_LONG).show()
    }

    fun String.makeShortToast(){
        Toast.makeText(this@BaseActivity,this,Toast.LENGTH_SHORT).show()
    }

}