package com.moldedbits.genesis.widgets

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
import com.moldedbits.genesis.R
import com.moldedbits.genesis.models.response.TranslatableString
import timber.log.Timber


class TranslatableTextView : AppCompatTextView {

    interface TranslatableClickListener {
        fun onClick(original: String, translation: String, x: Int, y: Int)
    }

    private var clickListener: TranslatableClickListener? = null

    private val spans: MutableList<SpanContainer> = mutableListOf()
    private var highlightSpan: ForegroundColorSpan? = null

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
            val highlightColor = ContextCompat.getColor(context, R.color.colorAccent)
            highlightSpan = ForegroundColorSpan(highlightColor)
        } else {
            builder.removeSpan(highlightSpan)
        }

        builder.setSpan(highlightSpan, startIndex, endIndex,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)

        if (layout == null) {
            Timber.d("Layout is null")
        } else {
            // Get left most position
            var x = layout.getPrimaryHorizontal(startIndex).toInt()
            for (i in startIndex until endIndex) {
                x = minOf(x, layout.getPrimaryHorizontal(i).toInt())
            }

            val y = layout.getLineTop(layout.getLineForOffset(endIndex))
            clickListener?.onClick(toHighlight, translation, x, y)
            Timber.d("Position is %d, %d", x, y)
        }

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

//    class HighlightSpan(private val backgroundColor: Int,
//                        private val padding: Int) : LineBackgroundSpan {
//
//        private val bgRect = Rect()
//
//        override fun drawBackground(c: Canvas?, p: Paint?, left: Int, right: Int, top: Int,
//                                    baseline: Int, bottom: Int, text: CharSequence?, start: Int,
//                                    end: Int, lnum: Int) {
//            Timber.d("Highlighting \"%s\"", text?.substring(start, end))
//            val textWidth = Math.round(p!!.measureText(text, start, end))
//            val paintColor = p.color
//            // Draw the background
//            bgRect.set(left - padding,
//                    top - if (lnum == 0) padding / 2 else -(padding / 2),
//                    left + textWidth + padding,
//                    bottom + padding / 2)
//            p.color = backgroundColor
//            c?.drawRect(bgRect, p)
//            p.color = paintColor
//        }
//    }
}
