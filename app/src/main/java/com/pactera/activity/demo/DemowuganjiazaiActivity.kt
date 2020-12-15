package com.pactera.activity.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pactera.R
import com.google.android.material.tabs.TabLayoutMediator
import com.pactera.activity.base.BaseActivity
import com.pactera.adapter.viewpageradapter.AdapterFragmentPager
import com.pactera.fragment.demo.DemowuganjiazaiAfterFragment
import com.pactera.fragment.demo.DemowuganjiazaiRawFragment
import kotlinx.android.synthetic.main.fragment_wuganjiazai.*
import java.io.Serializable

/**
 * 无感加载
 */
class DemowuganjiazaiActivity: BaseActivity() {

    override fun initView(savedInstanceState: Bundle?) {
        setContentView(R.layout.fragment_wuganjiazai)
        fragment_wuganjiazai_tablayout.addTab(fragment_wuganjiazai_tablayout.newTab());
        fragment_wuganjiazai_tablayout.addTab(fragment_wuganjiazai_tablayout.newTab());

        val adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount() = 2

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> DemowuganjiazaiRawFragment()
                    else -> DemowuganjiazaiAfterFragment()
                }
            }

        }

        fragment_wuganjiazai_viewpager2.adapter = adapter

        TabLayoutMediator(fragment_wuganjiazai_tablayout, fragment_wuganjiazai_viewpager2, false){tab, pos->
            when(pos){
                0-> tab.text = resources.getText(R.string.wuyouhua)
                1-> tab.text = resources.getText(R.string.youhuahou)
                else ->{

                }
            }
        }.attach()

    }

}