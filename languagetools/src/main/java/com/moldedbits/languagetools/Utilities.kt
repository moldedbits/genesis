package com.moldedbits.languagetools

import android.content.res.Resources
import android.util.TypedValue

object Utilities {

    val ARTICLE_ID = "articleId"

    fun convertDpToPx(pixel: Int, res: Resources): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel.toFloat(),
                res.displayMetrics)
    }
}