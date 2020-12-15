package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linearlayout_vertical.view.*
import android.view.MotionEvent
import android.widget.ImageButton


/**
 *  主页 里面的 Linearlayout
 */
class YSLinearLayoutVertical(context : Context, attrs: AttributeSet) :LinearLayout(context,attrs){

    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linearlayout_vertical, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutVertical)

        ys_linearlayout_main_textview.text = attributes.getString(R.styleable.YSLinearLayoutVertical_title_text)
//        ys_linear_layout_vertical_image_button.setImageResource(attributes.getResourceId(R.styleable.YSLinearLayoutVertical_image_url,0))

        attributes.recycle()

    }


    fun getImageButton(): ImageButton{
        return ys_linear_layout_vertical_image_button
    }


    /**
     *  父组件的事件拦截，杜绝点击子组件不灵敏
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return true
    }

}