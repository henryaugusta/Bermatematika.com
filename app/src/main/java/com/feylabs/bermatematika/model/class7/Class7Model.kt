package com.feylabs.bermatematika.model.class7

class Class7Model (){
    lateinit var name: String
    lateinit var formula: String
    lateinit var expl: String

    lateinit var data : MutableList<Class7Model>

//    constructor(name : String,formula : String,explanation : String){
//        this.name = name
//        this.formula = formula
//        this.expl = explanation
//    }

    fun setFormulaData(newData : MutableList<Class7Model>){
        this.data.addAll(newData)
    }

    fun getFormulaData() : MutableList<Class7Model> = data

}