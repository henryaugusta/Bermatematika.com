package com.feylabs.bermatematika.model.notasi

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.feylabs.bermatematika.util.Url
import org.json.JSONObject

data class NotasiModel(
    var notation: String = "",
    var read: String = "",
    var desc: String = "",
    var audioPath: String = "",
    var createdAt: String = "",
    var deletedAt: String = ""
) {





}