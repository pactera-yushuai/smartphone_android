package com.pactera.activity

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.pactera.R
import com.pactera.activity.base.BaseActivity
import com.pactera.fragment.*
import com.pactera.fragment.base.BaseFragment


/**
 *  新主页
 */
class HomeActivity : BaseActivity() {

    private var welcomeFragment: WelcomeFragment? = null
    private var guideFragment: GuideFragment? = null
    private var mainFragment: MainFragment? = null
    private var weiTaoFragment: WeiTaoFragment? = null
    private var detailFragment: DetailFragment? = null

    private var currentFragment: Fragment? = null

    private val fragmentList = HashMap<String, Fragment>(3)

    val WELCOME_FRAGMENT = "welcomeFragment"
    val GUIDE_FRAGMENT = "AdsFragment"
    val MAIN_FRAGMENT = "mainFragment"
    val WEITAO_FRAGMENT = "weitaoFragment"
    val DETAIL_FRAGMENT = "detailFragment"

    val delayMillis1 = 1800.toLong()
    val delayMillis2 = 9000.toLong()

    val runnable = Runnable { switchFragment(MAIN_FRAGMENT) }

    lateinit var handler: Handler

    override fun initView(savedInstanceState: Bundle?) {

        handler = Handler(mainLooper)

        setContentView(R.layout.activity_home)

        initFragmentList(savedInstanceState)

        // 一开始选中mainFragment
        switchFragment(WELCOME_FRAGMENT)

//        replaceFragment(welcomeFragment!!, WELCOME_FRAGMENT)

        handler.postDelayed({
            switchFragment(GUIDE_FRAGMENT)
//            replaceFragment(guideFragment!!, GUIDE_FRAGMENT)
        }, delayMillis1)

        handler.postDelayed(runnable, delayMillis2+delayMillis1)

//        Handler(mainLooper).postDelayed({
////            switchFragment(MAIN_FRAGMENT)
//            if(savedInstanceState==null){
//                replaceFragment(mainFragment!!, MAIN_FRAGMENT)
//            }
//        }, 2000)

    }


    fun initFragmentList(savedInstanceState: Bundle?){

        // 根据 savedInstanceState 的状态，看看是不是需要重新初始化 Fragment，防止fragment重叠
        if(savedInstanceState==null){

            if(mainFragment==null){
                mainFragment = MainFragment()
                if(!mainFragment!!.isAdded){
                    fragmentList[MAIN_FRAGMENT] = mainFragment!!
                    if(!supportFragmentManager.isDestroyed){
                        supportFragmentManager.beginTransaction().add(R.id.activity_home_framelayout, mainFragment!!, MAIN_FRAGMENT).commit()
                    }
                }
            }

            if(guideFragment==null){
                Handler(mainLooper).postDelayed({
                    guideFragment = GuideFragment()
                    if(!guideFragment!!.isAdded){
                        fragmentList[GUIDE_FRAGMENT] = guideFragment!!
                        if(!supportFragmentManager.isDestroyed) {
                            supportFragmentManager.beginTransaction().add(R.id.activity_home_framelayout, guideFragment!!, GUIDE_FRAGMENT).commit()
                        }
                    }
                }, delayMillis1)

            }

            if(weiTaoFragment==null){
                weiTaoFragment = WeiTaoFragment()
                if(!weiTaoFragment!!.isAdded){
                    fragmentList[WEITAO_FRAGMENT] = weiTaoFragment!!
                    if(!supportFragmentManager.isDestroyed) {
                        supportFragmentManager.beginTransaction().add(R.id.activity_home_framelayout, weiTaoFragment!!, WEITAO_FRAGMENT).commit()
                    }
                }
            }

            if(welcomeFragment == null){
                welcomeFragment = WelcomeFragment()
                if(!welcomeFragment!!.isAdded){
                    fragmentList[WELCOME_FRAGMENT] = welcomeFragment!!
                    if(!supportFragmentManager.isDestroyed) {
                        supportFragmentManager.beginTransaction().add(R.id.activity_home_framelayout, welcomeFragment!!, WELCOME_FRAGMENT).commit()
                    }
                }
            }

            if(detailFragment == null){
                detailFragment = DetailFragment()
                if(!detailFragment!!.isAdded){
                    fragmentList[DETAIL_FRAGMENT] = detailFragment!!
                    if(!supportFragmentManager.isDestroyed) {
                        supportFragmentManager.beginTransaction().add(R.id.activity_home_framelayout, detailFragment!!, DETAIL_FRAGMENT).commit()
                    }
                }
            }

        }else{

            if(mainFragment == null){
                val temp = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT)
                mainFragment = if(temp != null){
                    temp as MainFragment
                }else{
                    MainFragment()
                }
            }

            if(welcomeFragment == null){
//                welcomeFragment = supportFragmentManager.findFragmentByTag(WELCOME_FRAGMENT) as WelcomeFragment
                val temp = supportFragmentManager.findFragmentByTag(WELCOME_FRAGMENT)
                welcomeFragment = if(temp != null){
                    temp as WelcomeFragment
                }else{
                    WelcomeFragment()
                }
            }

            if(guideFragment == null){
                handler.postDelayed({
                    val temp = supportFragmentManager.findFragmentByTag(GUIDE_FRAGMENT)
                    guideFragment = if(temp!=null){
                        temp as GuideFragment
                    }else{
                        GuideFragment()
                    }
                }, delayMillis1)

            }

            if(weiTaoFragment == null){
                val temp = supportFragmentManager.findFragmentByTag(WEITAO_FRAGMENT)
                weiTaoFragment = if(temp!=null){
                    temp as WeiTaoFragment
                }else{
                    WeiTaoFragment()
                }
            }

            if(detailFragment == null){
                val temp = supportFragmentManager.findFragmentByTag(DETAIL_FRAGMENT)
                detailFragment = if(temp!=null){
                    temp as DetailFragment
                }else{
                    DetailFragment()
                }
            }

            fragmentList[WELCOME_FRAGMENT] = welcomeFragment!!
            fragmentList[MAIN_FRAGMENT] = mainFragment!!
            fragmentList[GUIDE_FRAGMENT] = guideFragment!!
            fragmentList[WEITAO_FRAGMENT] = weiTaoFragment!!
            fragmentList[DETAIL_FRAGMENT] = detailFragment!!

        }

    }


    /**
     * switch home page fragment
     */
    fun switchFragment(toFragment: String) {

        val manager = supportFragmentManager
        if(!manager.isDestroyed){
            val ft = manager.beginTransaction()

            fragmentList.forEach {
                if(it.key == toFragment){
                    ft.show(fragmentList[it.key]!!)
                    currentFragment = fragmentList[it.key]!!
                }else{
                    ft.hide(fragmentList[it.key]!!)
                }
            }

            ft.commit()
        }

    }

    fun replaceFragment(toFragment: Fragment, tag: String) {

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        ft.replace(R.id.activity_home_framelayout, toFragment, tag)
//                .addToBackStack(tag)

        ft.commit()

    }

    override fun onBackPressed() {
        println("还剩： " + supportFragmentManager.backStackEntryCount)
        println("fragments： " + supportFragmentManager.fragments)
        println("primaryNavigationFragment： " + supportFragmentManager.primaryNavigationFragment)
        println("fragmentFactory： " + supportFragmentManager.fragmentFactory)
//        if(supportFragmentManager.backStackEntryCount==0){
//            finish()
//        }
        if(currentFragment==mainFragment){
            finish()
        }else{
            switchFragment(MAIN_FRAGMENT)
        }
    }

}
