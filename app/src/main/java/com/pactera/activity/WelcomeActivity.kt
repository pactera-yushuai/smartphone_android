package com.pactera.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import com.pactera.R
import com.pactera.activity.base.BaseActivity
import com.pactera.annotation.VMScope
import com.pactera.extension.injectViewModel
import com.pactera.viewmodel.HomeActivityViewModel
import com.pactera.viewmodel.fragment.HomeFragmentViewModel

/**
 * 欢迎页
 */

class WelcomeActivity : BaseActivity() {

    public lateinit var imageView: ImageView
    private lateinit var alphaAnimation: AlphaAnimation

    @VMScope("temp")
    lateinit var homeActivityViewModel: HomeActivityViewModel

    @VMScope("fragment")
    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun initView(savedInstanceState: Bundle?) {

        injectViewModel()

        homeActivityViewModel.setData("oh,yeah~")

        homeFragmentViewModel.getList()

        //设置无标题 就是这句话 导致很多手机闪退 一定一定要注释掉
//        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //设置全屏
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        imageView = ImageView(applicationContext)
        imageView.scaleType = ImageView.ScaleType.FIT_XY

        imageView.setImageResource(R.mipmap.shanping)
        setContentView(imageView)

        alphaAnimation = AlphaAnimation(0.1f, 1f)
        alphaAnimation.fillAfter = true
        alphaAnimation.duration = 2000
        alphaAnimation.repeatCount = 0
        imageView.startAnimation(alphaAnimation)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                //动画结束,跳转
                startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
//                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }

        })

    }


}