package com.pactera.adapter.viewpageradapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pactera.fragment.base.BaseFragment
import com.pactera.fragment.demo.DemowuganjiazaiAfterFragment
import com.pactera.fragment.demo.DemowuganjiazaiRawFragment


class AdapterFragmentPager(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<BaseFragment> = SparseArray()

    init {
        fragments.put(PAGE_HOME, DemowuganjiazaiRawFragment())
        fragments.put(PAGE_FIND, DemowuganjiazaiAfterFragment())
//        fragments.put(PAGE_INDICATOR, IndicatorFragment.getInstance())
//        fragments.put(PAGE_OTHERS, OthersFragment.getInstance())
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }

    companion object {

        const val PAGE_HOME = 0

        const val PAGE_FIND = 1

        const val PAGE_INDICATOR = 2

        const val PAGE_OTHERS = 3

    }

}