package com.moldedbits.languagetools.widgets

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.moldedbits.languagetools.R
import com.moldedbits.languagetools.Utilities

abstract class TranslationFragment : Fragment(), TranslatableTextView.TranslatableClickListener {

    private var popupWindow: PopupWindow

    private var popupPadding: Int = 0

    init {
        val rootView = LayoutInflater.from(context)
                .inflate(R.layout.popup_window, null)
        popupWindow = PopupWindow(rootView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        // Close window on outside click
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.isOutsideTouchable = true

        rootView.setOnClickListener { popupWindow.dismiss() }

        popupWindow.setOnDismissListener({ getTranslatableView().removeHighlight() })

        popupPadding = Utilities.convertDpToPx(20, resources).toInt()
    }

    abstract fun getTranslatableView(): TranslatableTextView

    override fun onClick(original: String, translation: String, x: Int, y: Int) {
        val rootView = popupWindow.contentView

        (rootView.findViewById<View>(R.id.text_translation) as TextView).text = translation
        popupWindow.showAsDropDown(getTranslatableView(), x, y + popupPadding)
    }
}
