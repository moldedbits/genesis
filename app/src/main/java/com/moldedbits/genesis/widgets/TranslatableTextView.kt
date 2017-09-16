package com.moldedbits.genesis.widgets

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import com.moldedbits.genesis.models.response.TranslatableString

class TranslatableTextView : AppCompatTextView {

    interface TranslatableClickListener {
        fun onClick(original: String, translation: String)
    }

    var clickListener: TranslatableClickListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    fun setTranslatableClickListener(listener: TranslatableClickListener) {
        this.clickListener = listener
    }

    fun setText(translatableString: TranslatableString) {
        setText(translatableString.spanish, listOf(translatableString))
    }

    fun setText(passage: String, sentences: List<TranslatableString>) {
        val builder = SpannableStringBuilder()
        builder.append(passage)

        for ((english, spanish) in sentences) {
            val startIndex = passage.indexOf(spanish)
            val endIndex = startIndex + spanish.length

            val span = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    clickListener?.onClick(spanish, english)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                    ds.color = this@TranslatableTextView.currentTextColor
                }
            }

            builder.setSpan(span, startIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        text = builder
        movementMethod = LinkMovementMethod.getInstance()
    }
}
