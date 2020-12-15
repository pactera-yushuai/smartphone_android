package com.pactera.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
import com.pactera.adapter.viewpageradapter.BannerAdapter
import com.pactera.bean.Banner
import com.pactera.bean.WeiTao1
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.view_scroll.view.*
import kotlinx.android.synthetic.main.view_ten_item.view.*
import kotlinx.android.synthetic.main.ys_toolbar2.view.*

class ViewHScroll(context: Context, attrs: AttributeSet?): RelativeLayout(context, attrs){

    private var mActivity = context as Activity

    init {

        LayoutInflater.from(context).inflate(R.layout.view_scroll, this, true)



//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSToolBar2)
//
//        ys_toolbar2_button_left.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_left_button_text)
//        ys_toolbar2_textview.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_middle_text_view_text)
//        ys_toolbar2_button_right.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_right_button_text)
//
//        attributes.recycle()

    }

    fun getRecyclerView(): RecyclerView{
        return view_scroll_recyclerview
    }

    fun setData(banners: List<WeiTao1>){
//        view_ten_banner.addBannerLifecycleObserver(context as LifecycleOwner)//添加生命周期观察者
//                .setAdapter(BannerAdapter(banners))
//                .indicator = CircleIndicator(mActivity);
    }

}