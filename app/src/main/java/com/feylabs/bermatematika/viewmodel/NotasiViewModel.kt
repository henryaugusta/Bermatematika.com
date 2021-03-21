package com.feylabs.bermatematika.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.feylabs.bermatematika.model.notasi.NotasiModel
import com.feylabs.bermatematika.util.Url
import org.json.JSONObject

class NotasiViewModel : ViewModel() {

    var notasiLiveData = MutableLiveData<MutableList<NotasiModel>>()

    fun getNotasi() {
        val tempNotationList = mutableListOf<NotasiModel>()

        AndroidNetworking.post(Url.GET_NOTATION)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("http_response", response.toString())
                    if (response.getInt("length") > 0) {
                        val razky = response.getJSONArray("data")
                        Log.d("panjang",razky.length().toString())
                        for (i in 0 until razky.length()) {
                            val data = razky.getJSONObject(i)
                            Log.d("http_response_notation", data.getString("notation"))
                            tempNotationList.add(
                                NotasiModel(
                                    notation = data.getString("notation"),
                                    read = data.getString("read"),
                                    desc = data.getString("description"),
                                    audioPath = data.getString("audio_path"),
                                    createdAt = data.getString("created_at"),
                                    deletedAt = data.getString("deleted_at")
                                )
                            )
                        }
                        notasiLiveData.value = tempNotationList
                    } else {
                        Log.d("http_res", "No Data")
                    }

                }

                override fun onError(anError: ANError) {
                    Log.e("htpp_error", anError.message.toString())
                }

            })


    }



}

