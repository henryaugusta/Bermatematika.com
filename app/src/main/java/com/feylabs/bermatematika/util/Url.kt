package com.feylabs.bermatematika.util

class Url {
    companion object{
        const val BASE_URL = "https://math.feylaboratory.xyz/api/"
        const val GET_NOTATION = "https://math.feylaboratory.xyz/api/notation/fetch"

        fun FORMULA_BY_CLASS (class_id : String) : String{
            //http://math.feylaboratory.xyz/api/formula/1/fetch
            val fetchURL = "${BASE_URL}formula/${class_id}/fetch"
            return fetchURL
        }
    }
}