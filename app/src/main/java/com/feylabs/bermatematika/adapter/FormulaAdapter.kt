package com.feylabs.bermatematika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.ListFormulaBinding
import com.feylabs.bermatematika.model.formula.FormulaModel

class FormulaAdapter : RecyclerView.Adapter<FormulaAdapter.FormulaViewHolder>(){
    var dataFormula = mutableListOf<FormulaModel>()
    lateinit var mFormulaListInterface : FormulaListInterface

    fun setFormulaInterface(mFormulaListInterface : FormulaListInterface){
        this.mFormulaListInterface = mFormulaListInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormulaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_formula,parent,false)
        return FormulaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormulaViewHolder, position: Int) {
        holder.onBind(dataFormula[position])
    }

    override fun getItemCount(): Int {
        return dataFormula.size
    }


    inner class FormulaViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val viewBinding = ListFormulaBinding.bind(v)
        fun onBind(formulaModel: FormulaModel){
            viewBinding.labelFormulaName.text = formulaModel.name

            viewBinding.baseView.setOnClickListener {
                mFormulaListInterface.onclick(formulaModel)
            }

        }
    }


    interface FormulaListInterface{
        fun onclick(formulaModel : FormulaModel)
    }



}




