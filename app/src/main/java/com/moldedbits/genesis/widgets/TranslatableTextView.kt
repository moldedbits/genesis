package com.moldedbits.genesis.widgets

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.moldedbits.genesis.models.response.TranslatableString

class TranslatableTextView : AppCompatTextView {

    var translation: String = ""

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    fun setText(translatableString: TranslatableString) {
        text = translatableString.spanish
        translation = translatableString.english
    }

    fun setText(passage: String, sentences: List<TranslatableString>) {
        val builder = SpannableStringBuilder()
        builder.append(passage)

        for ((english, spanish) in sentences) {
            val startIndex = passage.indexOf(spanish)
            val endIndex = startIndex + spanish.length

            val span = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    Toast.makeText(context, english, Toast.LENGTH_SHORT).show()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = Color.parseColor("#333333")
                }
            }

            builder.setSpan(span, startIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        text = builder
        movementMethod = LinkMovementMethod.getInstance()
    }
}
