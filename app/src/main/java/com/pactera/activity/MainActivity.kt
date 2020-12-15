package com.pactera.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pactera.R
import com.pactera.fragment.demo.DemoThumbnailRawFragment
import com.pactera.common.Common
import com.pactera.fragment.DemosFragment
import com.pactera.fragment.base.BaseFragment
import com.pactera.fragment.DownloadFragment

import com.pactera.utils.SharedPreferencesUtils
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pactera.activity.base.BaseActivity
import com.pactera.annotation.VMScope
import com.pactera.extension.injectViewModel
import com.pactera.fragment.HomeFragment1
import com.pactera.fragment.demo.DemowuganjiazaiAfterFragment
import com.pactera.fragment.demo.DemowuganjiazaiRawFragment
import com.pactera.fragment.home.HomeFragment
import com.pactera.viewmodel.HomeActivityViewModel
import com.pactera.viewmodel.fragment.HomeFragmentViewModel
//import com.bigkoo.alertview.AlertView
//import com.bigkoo.alertview.OnItemClickListener
//import com.nostra13.universalimageloader.utils.L
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pilot.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.properties.Delegates


/**
 *  主 Activity
 */
class MainActivity : BaseActivity(){

    private val TAG = "MainActivity"



    private var homeFragment : HomeFragment? = null
    private var demosFragment : DemosFragment? = null
    private var downloadFragment : DownloadFragment? = null

    var savedInstanceState: Bundle? = null

    val ORGANIZATION_FRAGMENT = "OrganizationFragmentTab"
    val RACE_FRAGMENT = "raceFragment"
    val SEARCH_PIGEON_FRAGMENT = "searchPigeonFragment"
    val ME_FRAGMENT = "meFragment"

    private val fragmentList = ArrayList<BaseFragment>(4)

    //for receive customer msg from jpush server
    private var mMessageReceiver: MessageReceiver? = null





    var organizationLongitude = ""

    var organizationLatitude = ""

    companion object {

        var isForeground = false

        var currentLongitude = 0.0.toDouble()
        var currentLatitude = 0.0.toDouble()
        val KEY_EXTRAS = "extras"

        var instance: MainActivity by Delegates.notNull()

    }

    init {
        instance = this
        Log.d(TAG, "init: 进入主界面 用户token："+ SharedPreferencesUtils.getstring("token",""))
    }

    @VMScope("temp")
    lateinit var homeActivityViewModel: HomeActivityViewModel

    @VMScope("fragment")
    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun initView(savedInstanceState: Bundle?) {

        injectViewModel()

        println("homeActivityViewModel: "+homeActivityViewModel.getData())

        homeFragmentViewModel.list2.observe(this){
            println("oh,yeah~"+it)
        }

        Log.d(TAG, "用户类型："+ SharedPreferencesUtils.getstring("role",""))

        setContentView(R.layout.activity_main)

        val adapter = object : FragmentStateAdapter(this) {

            override fun getItemCount() = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment1()
                    1 -> DownloadFragment()
                    else -> DemosFragment()
                }
            }

        }

        activity_main_view_pager2.adapter = adapter

        activity_main_radiobutton_home.setOnClickListener {

        }

        activity_main_radiobutton_me.setOnClickListener {

        }

        activity_main_radiobutton_search.setOnClickListener {

        }


    }



    // 普通用户走这个
    private fun initOrdinaryView(savedInstanceState: Bundle?) {




//        addPigeonryActivity.iv_head.observe(this, Observer {
            //            val numbers = messageNumbersBean.array
//            if (numbers != null && numbers.size > 0) {
//                val messageNumber = numbers[0]
//
//                if (messageNumber.myRelease > 0) {
//                    //发单数量
//                    QBadgeView(this@MainActivity).bindTarget(rbTabRelease).setBadgeNumber(messageNumber.myRelease).setBadgeGravity(Gravity.END or Gravity.TOP).setGravityOffset(20f, 0f, true)
//                }
//                if (messageNumber.myAccept > 0) {
//                    //接单数量
//                    QBadgeView(this@MainActivity).bindTarget(rbTabMy).setBadgeNumber(messageNumber.myAccept).setBadgeGravity(Gravity.END or Gravity.TOP).setGravityOffset(20f, 0f, true)
//                }
//                if (messageNumber.unreadMessage > 0) {
//                    //定制借款产品数量
//                    val homeFragment = fragmentList.get(INDEX_TAB_HOME) as HomeFragment
//                    homeFragment.setCustomNumber(messageNumber.unreadMessage)
//                }
//            }
//        })

        // 注册广播
        registerMessageReceiver()

//        with(activity_main_navigation){
//
//            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//
//            val resources = resources
//
//            val csl = resources.getColorStateList(R.color.navigation_menu_item_color)
//
//            itemTextColor = csl
//
//            // 解决替换自定义图标不生效
//            //itemIconTintList = null
//
//            BottomNavigationViewHelper.disableShiftMode(activity_main_navigation);
//
//            labelVisibilityMode = 0
//
//
//        }

    }


    fun initLiberatorData(){

        super.initData()


        val map = HashMap<String,String>()

        map["filter.organization_id"] = SharedPreferencesUtils.getstring("organization_id","")


    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
//        initOrdinaryView(savedInstanceState)
    }





    /**
     * switch home page fragment
     */
    fun switchFragment(toFragment: BaseFragment) {

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        fragmentList.forEach {
            if(it == toFragment){
                ft.show(it)
            }else{
                ft.hide(it)
            }
        }

        ft.commit()

    }

    override fun onResume() {

        super.onResume()

        isForeground = true

        // 普通用户
//        if(SharedPreferencesUtils.getstring("role","") == "ordinary"){
//            initOrdinaryView(savedInstanceState)
//        }
        // 放飞员
        if(SharedPreferencesUtils.getstring("role","") == "liberator"){
            initLiberatorData()
        }


    }



    override fun onBackPressed() {
        doubleClick()
    }



    private var isExit = false

    private fun doubleClick() {
        if (!isExit) {
            isExit = true // 准备退出
            Common.showToast(this, getString(R.string.hint_of_exit))
            Handler().postDelayed({
                isExit = false // 取消退出
            }, 1000)
        } else {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        isForeground = false
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mMessageReceiver != null)
            unregisterReceiver(mMessageReceiver)
    }


    /**
     *  重写这个方法，解决fragment重叠
     */
    override fun onSaveInstanceState(outState: Bundle) {

        Log.i("MainActivity ","onSaveInstanceState")

        /*fragment不为空时 保存*/
        homeFragment?.let {
            supportFragmentManager.putFragment(outState, ORGANIZATION_FRAGMENT, homeFragment!!)
        }
//
//        raceFragment?.let {
//            supportFragmentManager.putFragment(outState, RACE_FRAGMENT, raceFragment!!)
//        }

        demosFragment?.let {
            supportFragmentManager.putFragment(outState, SEARCH_PIGEON_FRAGMENT, demosFragment!!)
        }
        downloadFragment?.let {
            supportFragmentManager.putFragment(outState, ME_FRAGMENT, downloadFragment!!)
        }

        super.onSaveInstanceState(outState)

    }


    fun registerMessageReceiver() {
        mMessageReceiver = MessageReceiver()
        val filter = IntentFilter()
        filter.priority = IntentFilter.SYSTEM_HIGH_PRIORITY
        registerReceiver(mMessageReceiver, filter)
    }

    inner class MessageReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
//            if (MESSAGE_RECEIVED_ACTION == intent.action) {
//                val messge = intent.getStringExtra(KEY_MESSAGE)
//                val extras = intent.getStringExtra(KEY_EXTRAS)
//                val showMsg = StringBuilder()
//                showMsg.append("$KEY_MESSAGE : $messge \n")
//                if (extras.isNotBlank()) {
//                    showMsg.append("$KEY_EXTRAS : $extras \n")
//                }
//                //重置消息数量
////                addPigeonryActivity.sendRequestToGetNumbers()
//            }
        }

    }


}