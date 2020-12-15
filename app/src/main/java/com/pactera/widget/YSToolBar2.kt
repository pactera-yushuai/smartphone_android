package com.pactera.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_toolbar2.view.*

class YSToolBar2(context: Context, attrs:AttributeSet):RelativeLayout(context, attrs){

    private var mActivity = context as Activity

    init {

        LayoutInflater.from(context).inflate(R.layout.ys_toolbar2, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSToolBar2)

        ys_toolbar2_button_left.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_left_button_text)
        ys_toolbar2_textview.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_middle_text_view_text)
        ys_toolbar2_button_right.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_right_button_text)

        attributes.recycle()

    }

    fun getLeftButton():Button{
        return ys_toolbar2_button_left
    }

    fun getMiddleTextView():TextView{
        return ys_toolbar2_textview
    }

    fun getRightButton():Button{
        return ys_toolbar2_button_right
    }

}