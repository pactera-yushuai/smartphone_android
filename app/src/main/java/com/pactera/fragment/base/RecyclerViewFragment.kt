package com.pactera.fragment.base

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pactera.R
//import com.cjj.MaterialRefreshLayout
//import com.cjj.MaterialRefreshListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pactera.fragment.base.BaseFragment
import com.pactera.viewmodel.base.RecyclerViewViewModel2
import kotlinx.android.synthetic.main.activity_recyclerview.*
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import kotlinx.android.synthetic.main.framelayout_framelayout.*

/**
 *  带有RecyclerView基类
 */
abstract class RecyclerViewFragment: BaseFragment() {

    open var offset: Int = 0

    open var lastRefreshTime: Long = android.os.SystemClock.uptimeMillis()

    open var msg:String = ""

    open lateinit var mRecyclerView: RecyclerView

    open lateinit var recyclerViewViewModel: RecyclerViewViewModel2




    override fun initView(): View {
        recyclerViewViewModel = ViewModelProvider(this).get(RecyclerViewViewModel2::class.java)
        return View.inflate(mActivity, R.layout.fragment_recyclerview, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = fragment_recyclerview_recycler_view
    }



}
