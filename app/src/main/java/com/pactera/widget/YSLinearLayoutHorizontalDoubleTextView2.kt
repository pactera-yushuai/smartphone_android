package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pactera.R
import kotlinx.android.synthetic.main.ys_linear_layout_horizontal_double_text_view_2.view.*


/**
 *  Linearlayout 内嵌 TextView（左） TextView（右）
 */
class YSLinearLayoutHorizontalDoubleTextView2(context : Context, attrs: AttributeSet) :LinearLayout(context,attrs){


    init {

        LayoutInflater.from(context).inflate(R.layout.ys_linear_layout_horizontal_double_text_view_2, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSLinearLayoutHorizontalDoubleTextView2)

        ys_linear_layout_horizontal_double_text_view2_left.text =attributes.getString(R.styleable.YSLinearLayoutHorizontalDoubleTextView2_left_text_view_text2)

        ys_linear_layout_horizontal_double_text_view2_right.hint =attributes.getString(R.styleable.YSLinearLayoutHorizontalDoubleTextView2_right_text_view_text2)

        attributes.recycle()

    }


    /**
     * 左边 TextView
     */
    fun getLeftTextView(): TextView{
        return ys_linear_layout_horizontal_double_text_view2_left
    }

    /**
     * 右边 TextView
     */
    fun getRightTextView(): TextView{
        return ys_linear_layout_horizontal_double_text_view2_right
    }


}