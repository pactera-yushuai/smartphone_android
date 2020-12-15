package com.pactera.activity.demo

import android.os.Bundle
import com.pactera.R
import com.google.android.material.tabs.TabLayoutMediator
import com.pactera.activity.base.BaseActivity
import com.pactera.adapter.viewpageradapter.AdapterFragmentPager
import kotlinx.android.synthetic.main.fragment_wuganjiazai.*


/**
 *  缩略图
 */
class ThumbnailActivity: BaseActivity() {

//    var user: User? = null

    override fun initView(savedInstanceState: Bundle?) {

        setContentView(R.layout.fragment_wuganjiazai)
        fragment_wuganjiazai_tablayout.addTab(fragment_wuganjiazai_tablayout.newTab());
        fragment_wuganjiazai_tablayout.addTab(fragment_wuganjiazai_tablayout.newTab());

        val adapter = AdapterFragmentPager(this)

        fragment_wuganjiazai_viewpager2.adapter = adapter

        TabLayoutMediator(fragment_wuganjiazai_tablayout, fragment_wuganjiazai_viewpager2, false){tab, pos->
            when(pos){
                0->{}
                else->{}
            }
        }.attach()



//            AlertView(getString(R.string.alert_title), "确定要退出吗？", "取消", arrayOf("确定"), null, this, AlertView.Style.Alert, object :OnItemClickListener{
//
//                override fun onItemClick(o: Any?, position: Int) {
//                    println(o)
//                    println(position)
//                    when(position){
//                        -1 -> {}
//                        0 -> {
//                            SharedPreferencesUtils.removeAll()
//                            MyApplication.clearAllStatic()
//                            startActivity(Intent(this@SettingsActivity, LoginActivity::class.java))
//
//                            finish()
//                        }
//                        else -> {}
//                    }
//                }
//
//            }).setCancelable(true).setOnDismissListener{
//
//                println(it)
//
//            }.show()


    }


    override fun onResume() {

        super.onResume()

//        hud = KProgressHUD.create(this@SettingsActivity)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("获取用户信息")
//                .setDetailsLabel("请稍后")
//                .setCancellable(true)
//                .setAnimationSpeed(2)
//                .setDimAmount(0.5f)
//                .show()

    }

}
