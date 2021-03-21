package com.feylabs.bermatematika.adapter

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feylabs.bermatematika.R
import com.feylabs.bermatematika.databinding.ListNotationBinding
import com.feylabs.bermatematika.model.notasi.NotasiModel
import com.feylabs.bermatematika.util.ImageSRCRender

class NotasiAdapter : RecyclerView.Adapter<NotasiAdapter.NotasiViewHolder>() {

    lateinit var notasiInterface: NotasiInterface


    var notationData = arrayListOf<NotasiModel>()

    fun setInterface(notasiInterface : NotasiInterface) {
        this.notasiInterface = notasiInterface
    }

    fun setNotationData(data: MutableList<NotasiModel>) {
        notationData.clear()
        notationData.addAll(data)
    }

    inner class NotasiViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = ListNotationBinding.bind(v)
        fun bind(notasiModel: NotasiModel) {
            binding.imgTag.visibility=View.VISIBLE
            binding.labelNotationName.visibility=View.GONE

            binding.btnSpeak.setOnClickListener {
                notasiInterface.setSound(notasiModel)
            }

            binding.baseView.setOnClickListener {
                notasiInterface.setSound(notasiModel)
            }

            Log.d("notationz",notasiModel.notation)
            if (notasiModel.notation.contains("<img")) {
                val imgSRC =ImageSRCRender.getImageSrc(notasiModel.notation)
                Glide
                    .with(binding.root.context)
                    .load(imgSRC)
                    .fitCenter()
                    .into(binding.imgTag)
            }else{
                binding.imgTag.visibility=View.GONE
                binding.labelNotationName.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.labelNotationName.text = Html.fromHtml(notasiModel.notation, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    binding.labelNotationName.text = Html.fromHtml(notasiModel.notation)
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.labelDescNotasi.text =
                    Html.fromHtml(notasiModel.read, Html.FROM_HTML_MODE_COMPACT)
            } else {
//                binding.labelNotationName.text = Html.fromHtml(notasiModel.notation)
                binding.labelDescNotasi.text = Html.fromHtml(notasiModel.read)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasiViewHolder {
        return NotasiViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_notation, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return notationData.size
    }

    override fun onBindViewHolder(holder: NotasiViewHolder, position: Int) {
        holder.bind(notationData[position])
    }

    interface NotasiInterface {
        fun setSound(model : NotasiModel)
    }

}