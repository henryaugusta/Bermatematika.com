package com.feylabs.bermatematika.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.util.BaseActivity
import com.feylabs.bermatematika.viewmodel.FormulaViewModel

class FormulaActivity : BaseActivity() {
    lateinit var formulaViewModel : FormulaViewModel
    companion object{
        const val CLASS_ID = "CLASS_AIDI"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula)
        val class_id = intent.getStringExtra(CLASS_ID)

        formulaViewModel = ViewModelProvider(this).get(FormulaViewModel::class.java)
        formulaViewModel.getFormulaByClass(class_id.toString())
        formulaViewModel.formulaLiveData.observe(this, Observer {

        })

    }
}