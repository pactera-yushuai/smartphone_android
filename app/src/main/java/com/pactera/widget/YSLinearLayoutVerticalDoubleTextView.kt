package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linearlayout_vertical_double_textview.view.*


/**
 *  LinearLayout内嵌两个 TextView
 */
class YSLinearLayoutVerticalDoubleTextView (context : Context, attrs: AttributeSet) : LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_vertical_double_textview, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutVerticalDoubleTextView)

        ys_linearlayout_vertical_double_textview_top.text = attributes.getString(R.styleable.YSLinearLayoutVerticalDoubleTextView_ys_top_text_view_text)
        ys_linearlayout_vertical_double_textview_bottom.text = attributes.getString(R.styleable.YSLinearLayoutVerticalDoubleTextView_bottom_text_view_text)

        attributes.recycle()

    }


    fun getTopTextView(): TextView{
        return ys_linearlayout_vertical_double_textview_top
    }

    fun getBottomTextView(): TextView{
        return ys_linearlayout_vertical_double_textview_bottom
    }

}