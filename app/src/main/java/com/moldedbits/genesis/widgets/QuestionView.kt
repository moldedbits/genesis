package com.moldedbits.genesis.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.widget.CompoundButtonCompat
import android.support.v7.widget.AppCompatRadioButton
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import com.moldedbits.genesis.R
import com.moldedbits.genesis.models.response.Question

class QuestionView : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.question_view, this)
    }

    fun setQuestion(count: Int, question: Question) {
        (findViewById(R.id.question_text) as TextView).text = question.questionText.spanish
        (findViewById(R.id.question_count) as TextView).text = (count.toString() + ".")

        if (question.type.equals("multiple_choice", true)) {
            findViewById(R.id.answer_text).visibility = View.GONE

            val multipleContainer = findViewById(R.id.multiple_choice) as RadioGroup
            multipleContainer.visibility = View.VISIBLE
            multipleContainer.removeAllViews()
            for (option in question.options) {
                val radioButton = AppCompatRadioButton(context)
                radioButton.text = option
                radioButton.setTextColor(ContextCompat.getColor(context, R.color.text_light))
                CompoundButtonCompat.setButtonTintList(radioButton,
                        ContextCompat.getColorStateList(context, R.color.text_light))

                // Is this a correct option or not, store in a tag
                radioButton.tag = option == question.answerText

                multipleContainer.addView(radioButton)
            }
        } else {
            findViewById(R.id.multiple_choice).visibility = View.GONE

            val answerText = findViewById(R.id.answer_text) as TextView
            answerText.text = ""
            answerText.visibility = View.VISIBLE
        }
    }
}
