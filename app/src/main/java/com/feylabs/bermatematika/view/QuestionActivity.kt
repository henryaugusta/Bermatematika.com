package com.feylabs.bermatematika.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {

    var class_code = ""
    lateinit var pdf_source: String
    lateinit var viewBinding: ActivityQuestionBinding

    companion object {
        const val CLASS_CODE = "CLASS_CODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityQuestionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)


        class_code = intent.getStringExtra(CLASS_CODE).toString()
        Log.d("class_code_question",class_code)
        if (class_code=="7"){
            pdf_source=""
        }
        when (class_code) {
            "1" -> {
                pdf_source = "example_7.pdf"
            }
            "2" -> {
                pdf_source = "example_8.pdf"
            }
            "3" -> {
                pdf_source = "example_9.pdf"
            }
            "4" -> {
                pdf_source = "example_10.pdf"
            }
            "5" -> {
                pdf_source = "example_11.pdf"
            }
            "6" -> {
                pdf_source = "example_12.pdf"
            }
        }
        Log.d("pdf_source",pdf_source)

        viewBinding.pdfView.fromAsset(pdf_source)
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .defaultPage(0)
            .load()


    }
}