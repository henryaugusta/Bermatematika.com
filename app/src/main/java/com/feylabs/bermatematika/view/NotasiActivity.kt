package com.feylabs.bermatematika.view

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.feylabs.bermatematika.adapter.NotasiAdapter
import com.feylabs.bermatematika.databinding.ActivityNotasiBinding
import com.feylabs.bermatematika.model.notasi.NotasiModel
import com.feylabs.bermatematika.util.BaseActivity
import com.feylabs.bermatematika.viewmodel.NotasiViewModel
import java.util.*

class NotasiActivity : BaseActivity() {
    lateinit var viewBinding: ActivityNotasiBinding
    lateinit var notasiViewModel: NotasiViewModel
    lateinit var notasiAdapter: NotasiAdapter
    lateinit var mTTS: TextToSpeech

    var listNotasi = mutableListOf<NotasiModel>()

    override fun onPause() {
        if (mTTS != null && mTTS.isSpeaking()) {
            mTTS.stop()
        }
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNotasiBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val searchView = viewBinding.searchView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                notasiAdapter.filter.filter(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isBlank() ){
                    notasiViewModel.getNotasi()
                    notasiAdapter.setNotationData(listNotasi)
                }
                return false
            }

        })

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale("id", "ID")
            }
        })


        notasiViewModel = ViewModelProvider(this).get(NotasiViewModel::class.java)
        notasiViewModel.getNotasi()

        notasiAdapter = NotasiAdapter()
        notasiAdapter.setInterface(object : NotasiAdapter.NotasiInterface {

            override fun setSound(model: NotasiModel) {
                val toSpeak = model.read.toString()
                if (toSpeak.equals("")) {
                    "Tidak Ada Petunjuk Suara".makeShortToast()
                } else {
                    mTTS.speak(model.read, TextToSpeech.QUEUE_FLUSH, null)
                }
            }

        })


        viewBinding.loading.root.visibility = View.VISIBLE
        viewBinding.rvObject.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = notasiAdapter
        }

        notasiViewModel.notasiLiveData.observe(this, Observer {
            if (it.size != null) {
                viewBinding.loading.root.visibility = View.GONE
                listNotasi = it
                notasiAdapter.setNotationData(listNotasi)
                notasiAdapter.notifyDataSetChanged()
                Log.d("DATA STATUS", "ADA DATA")
                viewBinding.rvObject.adapter = notasiAdapter
                "Menampilkan ${it.size} Rumus".makeLongToast()
            } else {
                viewBinding.loading.root.visibility = View.GONE
                Log.d("DATA STATUS", "TIDAK ADA DATA")
                "Tidak Ada Data".makeLongToast()
            }

        })


    }
}