package com.pactera.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView

/**
 * Description: make GridView height self-adapted
 */
class MyGridView : GridView {

    var hasScrollBar = true

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        if (hasScrollBar) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2,
                    View.MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)// 注意这里,这里的意思是直接测量出GridView的高度
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}
