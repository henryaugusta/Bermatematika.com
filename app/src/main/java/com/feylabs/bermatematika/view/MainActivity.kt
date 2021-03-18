package com.feylabs.bermatematika.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.feylabs.bermatematika.databinding.ActivityMainBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {

    lateinit var vbinding: ActivityMainBinding
    lateinit var d : Date
    lateinit var meTime : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vbinding.root)

        val sdf = SimpleDateFormat("kk:mm:ss")
        d = Date()
        meTime = sdf.format(d)

        fixedRateTimer("timer",false,0,1000){
            this@MainActivity.runOnUiThread {
                d = Date()
                meTime = sdf.format(d)
                vbinding.textB.text = meTime
            }
        }


        val test2 = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(Date())
        val test4 = DateFormat.getDateInstance(DateFormat.ERA_FIELD).format(Date())
        Log.d("test1",meTime.toString())
        Log.d("test2",test2.toString())
        Log.d("test4",test4.toString())

        vbinding.textU.text = test4


        vbinding.btnStart.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

}