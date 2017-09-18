package com.moldedbits.genesis.widgets

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.view.View
import com.moldedbits.genesis.models.response.TranslatableString


class TranslatableTextView : AppCompatTextView {

    interface TranslatableClickListener {
        fun onClick(original: String, translation: String)
    }

    var clickListener: TranslatableClickListener? = null

    val spans: MutableList<SpanContainer> = mutableListOf()

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
        initSpans(passage, sentences)

        val builder = SpannableStringBuilder()
        builder.append(passage)

        for ((span, startIndex, endIndex) in spans) {
            builder.setSpan(span, startIndex, endIndex,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        text = builder
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initSpans(passage: String, sentences: List<TranslatableString>) {
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

            spans.add(SpanContainer(span, startIndex, endIndex))
        }
    }

    fun highlight(toHighlight: String) {
        val startIndex = text.indexOf(toHighlight)
        val endIndex = startIndex + toHighlight.length

        val builder = SpannableStringBuilder(text)
        builder.setSpan(UnderlineSpan(), startIndex, endIndex,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = builder
    }

    data class SpanContainer(val span: CharacterStyle, val startIndex: Int, val endIndex: Int)
}
