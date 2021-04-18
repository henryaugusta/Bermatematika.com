package com.feylabs.bermatematika.view

import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthurivanets.bottomsheets.config.Config
import com.feylabs.bermatematika.adapter.FormulaAdapter
import com.feylabs.bermatematika.adapter.FormulaListInterface
import com.feylabs.bermatematika.databinding.ActivityFormulaBinding
import com.feylabs.bermatematika.databinding.FormulaDetailBinding
import com.feylabs.bermatematika.model.formula.FormulaModel
import com.feylabs.bermatematika.util.BaseActivity
import com.feylabs.bermatematika.viewmodel.FormulaViewModel


class FormulaActivity : BaseActivity() {
    lateinit var formulaViewModel: FormulaViewModel
    lateinit var formulaAdapter: FormulaAdapter
    lateinit var viewBinding: ActivityFormulaBinding

    private lateinit var sp: SoundPool
    lateinit var formulaDetail: FormulaDetail

    private var spLoaded = false

    val listFormula = mutableListOf<FormulaModel>()

    companion object {
        const val CLASS_ID = "CLASS_AIDI"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewBinding = ActivityFormulaBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        sp = SoundPool.Builder()
            .setMaxStreams(10)
            .build()

        //Hide Loading Indicator
        viewBinding.loading.visibility = View.GONE

        val searchView = viewBinding.searchView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                formulaAdapter.filter.filter(query.toString())
                viewBinding.rvObject.recycledViewPool.clear();
                formulaAdapter.notifyDataSetChanged()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isBlank()) {
                    formulaViewModel.getFormulaByClass(intent.getStringExtra(CLASS_ID).toString())
//                    formulaAdapter.setFormulaData(listFormula)
                }
                return false
            }

        })


        formulaAdapter = FormulaAdapter()
        formulaDetail = FormulaDetail(this)

        formulaAdapter.setFormulaInterface(object : FormulaListInterface {
            override fun onclick(formulaModel: FormulaModel) {

                val lowerCaseStudy = formulaModel.formula.toLowerCase()

                val studyCase = lowerCaseStudy.substringAfterLast("contoh")

//                var studyCase = ""
//
//                studyCase = if (studyCase1!=""){
//                    studyCase1
//                }else{
//                    studyCase2
//                }

                val formulaPure = formulaModel.formula.substringBefore("Contoh")




                formulaModel.name.makeLongToast()
                formulaDetail.show()
                formulaDetail.formulaDetailBinding.apply {
                    labelDetailTitle.text = formulaModel.name
                    val html = "<html><body>${formulaPure}</body></html>"
                    webView.settings.javaScriptEnabled = true
                    webView.loadData(html, "text/html;charset=utf-8", "UTF-8")

                    val htmlConso = "<html><body>${studyCase}</body></html>"
                    webViewContohSoal.settings.javaScriptEnabled = true
                    webViewContohSoal.loadData(htmlConso, "text/html;charset=utf-8", "UTF-8")

                    btnCloseDetailFormula.setOnClickListener {
                        formulaDetail.dismiss(true)
                    }
                }

            }
        })
        val class_id = intent.getStringExtra(CLASS_ID)


        viewBinding.rvObject.let {
            it.layoutManager = LinearLayoutManager(applicationContext)
            it.setHasFixedSize(true)
            it.adapter = formulaAdapter
        }


        formulaViewModel = ViewModelProvider(this).get(FormulaViewModel::class.java)
        formulaViewModel.getFormulaByClass(class_id.toString())

        //SHOW LOADING INDICATOR
        viewBinding.loading.visibility = View.VISIBLE
        formulaViewModel.formulaLiveData.observe(this, Observer {
            if (it != null) {
                listFormula.clear()
                listFormula.addAll(it)
                viewBinding.loading.visibility = View.GONE
                "Menampilkan ${listFormula.size} Rumus".makeLongToast()
                formulaAdapter.setFormulaData(listFormula)
                formulaAdapter.notifyDataSetChanged()
            } else {
                viewBinding.loading.visibility = View.GONE
                "Belum Ada Data".makeLongToast()
            }
        })

    }
}