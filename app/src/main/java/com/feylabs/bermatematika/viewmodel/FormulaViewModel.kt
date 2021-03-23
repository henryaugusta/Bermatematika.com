package com.feylabs.bermatematika.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.feylabs.bermatematika.model.formula.FormulaModel
import com.feylabs.bermatematika.util.Url
import org.json.JSONObject
import java.net.URI

class FormulaViewModel : ViewModel() {

    val formulaLiveData = MutableLiveData<MutableList<FormulaModel>>()

    fun getFormulaByClass(class_id: String): MutableLiveData<MutableList<FormulaModel>> {
        val tempFormulaData = mutableListOf<FormulaModel>()
        AndroidNetworking.post(Url.FORMULA_BY_CLASS(class_id))
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("fan_formula_fetch",response.toString())
                    if (response.getInt("length") > 0) {
                        val razky = response.getJSONArray("data")
                        for (i in 0 until razky.length()) {
                            val data = razky.getJSONObject(i)
                            tempFormulaData.add(
                                FormulaModel(
                                    id = data.getString("id"),
                                    name = data.getString("name"),
                                    category = data.getString("category"),
                                    formula = data.getString("formula"),
                                    pdf_path = data.getString("pdf_path"),
                                    created_at = data.getString("created_at"),
                                    updated_at = data.getString("updated_at"),
                                    webview_detail= data.getString("id")
                                )
                            )
                        }
                        formulaLiveData.postValue(tempFormulaData)
                    }
                }

                override fun onError(anError: ANError?) {
                   Log.d("error_fan_formula",anError.toString())
                   Log.d("error_fan_formula",anError?.errorBody.toString())
                   Log.d("error_fan_formula",anError?.localizedMessage.toString())
                }
            })

        return formulaLiveData
    }


}