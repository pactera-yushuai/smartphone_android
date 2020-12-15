package com.pactera.fragment.demo

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.View
import android.widget.EditText

import com.pactera.R
import com.kaopiz.kprogresshud.KProgressHUD
import com.pactera.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.activity_forgetpassword.*

import java.util.HashMap

/**
 * 真正的骨架屏
 */
class DemoSkeletonAfterFragment : DemoSkeletonRawFragment() {

    private var editTexts:ArrayList<EditText> = ArrayList()

    override fun onResume() {
        super.onResume()
        homeFragmentViewModel.getList()
    }

    override fun dingzhi() {


    }


}
