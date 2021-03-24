package com.feylabs.bermatematika.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.arthurivanets.bottomsheets.BaseBottomSheet
import com.arthurivanets.bottomsheets.config.BaseConfig
import com.arthurivanets.bottomsheets.config.Config
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.FormulaDetailBinding

class FormulaDetail(
    hostActivity: Activity,
    config: BaseConfig = Config.Builder(hostActivity).build()
) : BaseBottomSheet(hostActivity, config) {

    lateinit var formulaDetailBinding: FormulaDetailBinding

    override fun onCreateSheetContentView(context: Context): View {
        val view = LayoutInflater.from(context).inflate(R.layout.formula_detail,this,false)
        Config.Builder(context).dismissOnTouchOutside(false).build()
        formulaDetailBinding = FormulaDetailBinding.bind(view)
        return formulaDetailBinding.root
    }

    fun closeDialog(){

    }

}