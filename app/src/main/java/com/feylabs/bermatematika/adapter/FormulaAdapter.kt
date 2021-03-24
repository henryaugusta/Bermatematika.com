package com.feylabs.bermatematika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.ListFormulaBinding
import com.feylabs.bermatematika.model.formula.FormulaModel
import java.util.*
import kotlin.collections.ArrayList

class FormulaAdapter : RecyclerView.Adapter<FormulaAdapter.FormulaViewHolder>() , Filterable{
    var dataFormula = mutableListOf<FormulaModel>()
    var dataFormulaSearch = mutableListOf<FormulaModel>()

    lateinit var mFormulaListInterface : FormulaListInterface

    fun setFormulaData(mData : MutableList<FormulaModel>){
        dataFormula.clear()
        this.dataFormula.addAll(mData)

        this.dataFormulaSearch.addAll(mData)
        dataFormulaSearch.clear()
    }

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


    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearched = constraint.toString()
                if (charSearched.isEmpty()){
                    dataFormulaSearch = dataFormula
                    notifyDataSetChanged()
                }else{
                    val resultList = ArrayList<FormulaModel>()
                    for (row in dataFormula){
                        if (row.name.toLowerCase(Locale.ROOT).contains(charSearched.toLowerCase(
                                Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    dataFormulaSearch= resultList
                }

                val filterResult = FilterResults()
                filterResult.values = dataFormulaSearch
                setFormulaData(dataFormulaSearch)
                notifyDataSetChanged()
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                notationDataFilter = results?.values as ArrayList<NotasiModel>
            }

        }
    }

}
interface FormulaListInterface{
    fun onclick(formulaModel : FormulaModel)
}






