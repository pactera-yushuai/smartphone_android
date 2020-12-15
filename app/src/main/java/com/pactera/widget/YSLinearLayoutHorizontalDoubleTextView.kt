package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linearlayout_horizontal_double_textview.view.*


/**
 *  LinearLayout内嵌两个 TextView
 */
class YSLinearLayoutHorizontalDoubleTextView (context : Context, attrs: AttributeSet) : LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_horizontal_double_textview, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutHorizontalDoubleTextView)

        ys_linearlayout_horizontal_double_textview_left.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalDoubleTextView_left_text_view_text)
        ys_linearlayout_horizontal_double_textview_right.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalDoubleTextView_right_text_view_text)

        attributes.recycle()

    }


    fun getLeftTextView(): TextView{
        return ys_linearlayout_horizontal_double_textview_left
    }


    fun getRightTextView(): TextView{
        return ys_linearlayout_horizontal_double_textview_right
    }


}