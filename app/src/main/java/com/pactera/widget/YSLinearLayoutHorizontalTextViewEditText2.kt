package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import android.widget.EditText
import kotlinx.android.synthetic.main.ys_linear_layout_horizontal_text_view_edit_text_2.view.*


/**
 *  Linearlayout 内嵌 TextView（左） EditText（右）
 */
class YSLinearLayoutHorizontalTextViewEditText2(context : Context, attrs: AttributeSet) :LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linear_layout_horizontal_text_view_edit_text_2, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutHorizontalTextViewEditText2)

        ys_linear_layout_horizontial_text_view_edit_text_text_view.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalTextViewEditText2_text_view_text2)

        ys_linear_layout_horizontial_text_view_edit_text_edit_text.hint =attributes.getString(R.styleable.YSLinearLayoutHorizontalTextViewEditText2_right_edit_text_hint2)

        attributes.recycle()

    }


    /**
     * 左边 TextView
     */
    fun getLeftTextView(): TextView{
        return ys_linear_layout_horizontial_text_view_edit_text_text_view
    }

    /**
     * 右边 EditText
     */
    fun getRightEditText(): EditText{
        return ys_linear_layout_horizontial_text_view_edit_text_edit_text
    }

}