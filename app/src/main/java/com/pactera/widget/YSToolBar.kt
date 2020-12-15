package com.pactera.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_toolbar.view.*

class YSToolBar(context: Context,attrs:AttributeSet):RelativeLayout(context, attrs){

    private var mActivity = context as Activity

    init {

        LayoutInflater.from(context).inflate(R.layout.ys_toolbar, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSToolBar)

        ys_toolbar_text_view_title.text = attributes.getString(R.styleable.YSToolBar_ystoolbar_middle_text_view_text)
        ys_toolbar_button_right.text = attributes.getString(R.styleable.YSToolBar_ystoolbar_right_button_text)

        attributes.recycle()

        //后退键
        ll_back.setOnClickListener { mActivity.onBackPressed() }


    }

    fun getMiddleTextView():TextView{
        return ys_toolbar_text_view_title
    }

    fun getLinearLayoutBack():LinearLayout{
        return ll_back
    }

    fun getRightButton():Button{
        return ys_toolbar_button_right
    }

}