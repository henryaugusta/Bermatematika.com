package com.feylabs.bermatematika.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.feylabs.bermatematika.R

class Glide {

    companion object{

        fun loadImageFromLocal(context : Context, image : Int,target : ImageView){
            Glide
                .with(context)
                .load(image)
                .into(target)
        }
    }

}