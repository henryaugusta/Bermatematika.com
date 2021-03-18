package com.feylabs.bermatematika.model.notasi

import com.feylabs.bermatematika.model.class7.Class7Model

class NotasiModel {
    lateinit var name: String
    lateinit var read: String
    lateinit var expl: String

    lateinit var data : MutableList<NotasiModel>

    constructor(name : String,read : String,explanation : String){
        this.name = name
        this.read = read
        this.expl = explanation
    }

    fun setFormulaData(newData : MutableList<NotasiModel>){
        this.data.addAll(newData)
    }

    fun getFormulaData() : MutableList<NotasiModel> = data
}