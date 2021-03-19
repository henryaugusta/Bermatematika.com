package com.feylabs.bermatematika.model.notasi

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.feylabs.bermatematika.model.class7.Class7Model
import org.json.JSONObject

data class NotasiModel(
    var name: String,
    var read: String,
    var desc: String,
    var audioPath: String
) {
    var listModel = mutableListOf<NotasiModel>()

    fun retrieveData(): MutableList<NotasiModel> {
        AndroidNetworking.post("URL")
            .addBodyParameter("", "")
            .addBodyParameter("", "")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    listModel.add(
                        NotasiModel(
                            "", "", "", ""
                        )
                    )
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })

        return listModel
    }


}