package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linear_layout_vertical_text_view_edit_text.view.*


/**
 *  LinearLayout内嵌删上下两个 TextView EditText
 */
class YSLinearLayoutVerticalTextViewEditText (context : Context, attrs: AttributeSet) : LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linear_layout_vertical_text_view_edit_text, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutVerticalTextViewEditText)

        ys_linear_layout_vertical_text_view_edit_text_text_view.text = attributes.getString(R.styleable.YSLinearLayoutVerticalTextViewEditText_ys_vertical_top_text_view_text)
        ys_linear_layout_vertical_text_view_edit_text_edit_text.hint = attributes.getString(R.styleable.YSLinearLayoutVerticalTextViewEditText_ys_vertical_bottom_edit_text_hint)

        attributes.recycle()

    }


    fun getTopTextView(): TextView {
        return ys_linear_layout_vertical_text_view_edit_text_text_view
    }

    fun getBottomEditText(): EditText {
        return ys_linear_layout_vertical_text_view_edit_text_edit_text
    }

}