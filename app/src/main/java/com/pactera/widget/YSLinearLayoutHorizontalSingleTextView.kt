package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pactera.R
import android.view.MotionEvent
import kotlinx.android.synthetic.main.ys_linearlayout_horizontal_single_textview.view.*


/**
 *  Linearlayout内嵌单个TextView
 */
class YSLinearLayoutHorizontalSingleTextView(context : Context, attrs: AttributeSet) :LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_horizontal_single_textview, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutHorizontalSingleTextView)

        ys_linearlayout_horizontal_single_textview_left.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalSingleTextView_title_text_h)

        attributes.recycle()

    }

    /**
     *  父组件的事件拦截，杜绝点击子组件不灵敏
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return true
    }

}