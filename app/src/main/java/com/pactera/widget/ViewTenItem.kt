package com.pactera.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import com.pactera.R
import com.pactera.activity.HomeActivity
import com.pactera.activity.demo.DemowuganjiazaiActivity
import com.pactera.activity.demo.SkeletonActivity
import com.pactera.adapter.viewpageradapter.BannerAdapter
import com.pactera.bean.Banner
import com.pactera.fragment.MainFragment.Companion.DOWNLOADFRAGMENT
import com.pactera.fragment.MainFragment.Companion.downloadFragment
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.view_ten_item.view.*
import kotlinx.android.synthetic.main.ys_toolbar2.view.*

class ViewTenItem(context: Context, attrs:AttributeSet?):RelativeLayout(context, attrs){

    private var mActivity = context as Activity

    init {

        LayoutInflater.from(context).inflate(R.layout.view_ten_item, this, true)

        with(fragment_home_yslinearlayout_1){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_new)
            setOnClickListener {
                mActivity.startActivity(Intent(mActivity, DemowuganjiazaiActivity::class.java))
            }
        }

        with(fragment_home_yslinearlayout_2){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_hot)
            setOnClickListener {
                downloadFragment?.let {
                    (mActivity as HomeActivity).mainFragment?.switchFragment(it)
                }
            }
        }

        with(fragment_home_yslinearlayout_3){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_supermarket)
            setOnClickListener {
                mActivity.startActivity(Intent(mActivity, SkeletonActivity::class.java))
            }
        }

        with(fragment_home_yslinearlayout_4){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_seafood)
            setOnClickListener {

            }
        }

        with(fragment_home_yslinearlayout_5){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_activity)
            setOnClickListener {

            }
        }

        with(fragment_home_yslinearlayout_6){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_discount)
            setOnClickListener {

            }
        }

        with(fragment_home_yslinearlayout_7){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_travel)
            setOnClickListener {

            }
        }

        with(fragment_home_yslinearlayout_8){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_auction)
            setOnClickListener {

            }
        }

        with(fragment_home_yslinearlayout_9){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_pay)
            setOnClickListener {

            }
        }

        with(fragment_home_yslinearlayout_10){
            getImageButton().setImageResource(com.pactera.R.mipmap.menu_more)
            setOnClickListener {

            }
        }

//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.YSToolBar2)
//
//        ys_toolbar2_button_left.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_left_button_text)
//        ys_toolbar2_textview.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_middle_text_view_text)
//        ys_toolbar2_button_right.text = attributes.getString(R.styleable.YSToolBar2_ystoolbar2_right_button_text)
//
//        attributes.recycle()

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

    fun setData(banners: List<Banner>){
        view_ten_banner.addBannerLifecycleObserver(context as LifecycleOwner)//添加生命周期观察者
                .setAdapter(BannerAdapter(banners))
                .indicator = CircleIndicator(mActivity);
    }

}