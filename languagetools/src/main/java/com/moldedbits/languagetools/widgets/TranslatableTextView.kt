package com.moldedbits.languagetools.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.View
import com.moldedbits.languagetools.R
import com.moldedbits.languagetools.models.TranslatableString
import timber.log.Timber


class TranslatableTextView : AppCompatTextView {

    interface TranslatableClickListener {
        fun onClick(original: String, translation: String, x: Int, y: Int)
    }

    private var clickListener: TranslatableClickListener? = null

    private val spans: MutableList<SpanContainer> = mutableListOf()
    private var highlightSpan: ForegroundColorSpan? = null

    var accentColor: Int = ContextCompat.getColor(context, R.color.colorAccent)

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
                    highlight(spanish, english)
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

    fun highlight(toHighlight: String, translation: String) {
        val startIndex = text.indexOf(toHighlight)
        val endIndex = startIndex + toHighlight.length

        val builder = SpannableStringBuilder(text)

        if (highlightSpan == null) {
            highlightSpan = ForegroundColorSpan(accentColor)
        } else {
            builder.removeSpan(highlightSpan)
        }

        builder.setSpan(highlightSpan, startIndex, endIndex,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Get left most position
        var x = layout.getPrimaryHorizontal(startIndex).toInt()
        for (i in startIndex until endIndex) {
            x = minOf(x, layout.getPrimaryHorizontal(i).toInt())
        }

        val y = layout.getLineTop(layout.getLineForOffset(endIndex))
        clickListener?.onClick(toHighlight, translation, x, y)
        Timber.d("Position is %d, %d", x, y)

        text = builder
    }

    fun removeHighlight() {
        if (highlightSpan != null) {
            val builder = SpannableStringBuilder(text)
            builder.removeSpan(highlightSpan)
            text = builder
        }
    }

    data class SpanContainer(val span: CharacterStyle, val startIndex: Int, val endIndex: Int)
}
