package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linearlayout_horizontal_wei_double_textview.view.*


/**
 *  LinearLayout内嵌两个 TextView
 */
class YSLinearLayoutHorizontalWeiDoubleTextView (context : Context, attrs: AttributeSet) : LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_horizontal_wei_double_textview, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutHorizontalWeiDoubleTextView)

        ys_linearlayout_horizontal_wei_double_textview_left.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalWeiDoubleTextView_wei_left_text_view_text)
        ys_linearlayout_horizontal_wei_double_textview_right.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalWeiDoubleTextView_wei_right_text_view_text)

        attributes.recycle()

    }


    fun getRightTextView(): TextView{
        return ys_linearlayout_horizontal_wei_double_textview_right
    }


}