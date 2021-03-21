package com.feylabs.bermatematika.util

class ImageSRCRender {

    companion object {
        fun getImageSrc(url: String) : String {
            val start: Int = url.indexOf("src=\"") + 5
            val end: Int = url.indexOf("\"", start)

            val src: String = url.substring(start, end)

            return  src
        }

    }


}