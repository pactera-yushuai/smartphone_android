package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linearlayout_vertical_textview_button_edittext.view.*


/**
 *  LinearLayout内嵌两个 TextView
 */
class YSLinearLayoutVerticalTextViewButtonEditText (context : Context, attrs: AttributeSet) : LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_vertical_textview_button_edittext, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutVerticalTextViewButtonEditText)

        ys_linearlayout_vertical_textview_button_edittext_tv.text = attributes.getString(R.styleable.YSLinearLayoutVerticalTextViewButtonEditText_ys_text_view_text)
        ys_linearlayout_vertical_textview_button_edittext_button.text = attributes.getString(R.styleable.YSLinearLayoutVerticalTextViewButtonEditText_ys_button_text)
//        ys_linearlayout_vertical_textview_button_edittext_button.visibility = attributes.getString(R.styleable.YSLinearLayoutVerticalTextViewButtonEditText_ys_button_text)

        attributes.recycle()

    }


    fun getTextView(): TextView{
        return ys_linearlayout_vertical_textview_button_edittext_tv
    }


    fun getButton(): Button{
        return ys_linearlayout_vertical_textview_button_edittext_button
    }

    fun getEditText(): EditText{
        return ys_linearlayout_vertical_textview_button_edittext_et
    }

}