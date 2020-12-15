package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import android.widget.EditText
import kotlinx.android.synthetic.main.ys_linearlayout_horizontal_textview_edittext.view.*


/**
 *  Linearlayout 内嵌 TextView（左） EditText（右）
 */
class YSLinearLayoutHorizontalTextViewEditText(context : Context, attrs: AttributeSet) :LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_horizontal_textview_edittext, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutHorizontalTextViewEditText)

        ys_linearlayout_horizontal_left_textview.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalTextViewEditText_text_view_text)

        ys_linearlayout_horizontal_right_edittext.hint =attributes.getString(R.styleable.YSLinearLayoutHorizontalTextViewEditText_right_edit_text_hint)

        attributes.recycle()

    }


    /**
     * 左边 TextView
     */
    fun getLeftTextView(): TextView{
        return ys_linearlayout_horizontal_left_textview
    }

    /**
     * 右边 EditText
     */
    fun getRightEditText(): EditText{
        return ys_linearlayout_horizontal_right_edittext
    }


}