package com.feylabs.bermatematika.view

import android.R
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arges.sepan.argmusicplayer.Enums.AudioType
import com.arges.sepan.argmusicplayer.IndependentClasses.ArgAudio
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerSmallView
import com.feylabs.bermatematika.adapter.NotasiAdapter
import com.feylabs.bermatematika.databinding.ActivityNotasiBinding
import com.feylabs.bermatematika.model.notasi.NotasiModel
import com.feylabs.bermatematika.util.BaseActivity
import com.feylabs.bermatematika.viewmodel.NotasiViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*


class NotasiActivity : BaseActivity() {
    lateinit var viewBinding: ActivityNotasiBinding
    lateinit var notasiViewModel: NotasiViewModel
    lateinit var notasiAdapter: NotasiAdapter
    lateinit var mTTS: TextToSpeech

    protected val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                notasiAdapter.filter.filter(query.toString())
                notasiAdapter.notifyDataSetChanged()

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isBlank()) {
                    notasiViewModel.getNotasi()
//                    notasiAdapter.setNotationData(listNotasi)
                }
                notasiAdapter.notifyDataSetChanged()
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
                try {
//                String url = "url-to-your-mp3-file.mp3"
//                AudioPlayerView audioPlayerView = (AudioPlayerView) findViewById(R.id.player);
//                audioPlayerView.withUrl(url);


                    val webView = viewBinding.webViewAudio
                    val soundURL = "https://math.feylaboratory.xyz/uploads/audio/${model.audioPath}"
                    val html = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "\n" +
                            "<audio controls style='width:100%'>\n" +
                            "  <source src=\"${soundURL}\" type=\"audio/mp4\">\n" +
                            "Your browser does not support the audio element.\n" +
                            "</audio>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>\n"
                    webView.settings.javaScriptEnabled=true
                    webView.loadData(html,"text/html;charset=utf-8", "UTF-8")


//                    val soundURL = "http://math.feylaboratory.xyz/uploads/audio/${model.audioPath}"
//                    val audio = ArgAudio("Math", model.read, soundURL, AudioType.URL)
//                    val argMusicPlayer = viewBinding.argmusicplayer as ArgPlayerSmallView
//                    argMusicPlayer.play(audio)

                } catch (e: Exception) {
                    println(e.toString())
                }

            }


        })


        viewBinding.loadingz.root.visibility = View.VISIBLE
        viewBinding.rvObject.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = notasiAdapter
        }

        notasiViewModel.notasiLiveData.observe(this, Observer {
            if (it.size != null) {
                viewBinding.loadingz.root.visibility = View.GONE
                listNotasi = it
                notasiAdapter.setNotationData(listNotasi)
                notasiAdapter.notifyDataSetChanged()
                Log.d("DATA STATUS", "ADA DATA")
                viewBinding.rvObject.adapter = notasiAdapter
                "Menampilkan ${it.size} Notasi".makeLongToast()
            } else {
                viewBinding.loadingz.root.visibility = View.GONE
                Log.d("DATA STATUS", "TIDAK ADA DATA")
                "Tidak Ada Data".makeLongToast()
            }

        })


    }
}