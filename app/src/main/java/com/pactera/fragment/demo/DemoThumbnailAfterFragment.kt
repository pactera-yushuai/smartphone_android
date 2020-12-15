package com.pactera.fragment.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pactera.R
import com.pactera.viewmodel.activity.LoginActivityViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import com.pactera.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 缩略图 小图
 */
class DemoThumbnailAfterFragment : BaseFragment() {


    private var rid: String = ""

    private lateinit var loginActivityViewModel: LoginActivityViewModel

    private lateinit var hud: KProgressHUD

    override fun initView(): View {
        TODO("Not yet implemented")
    }



}
