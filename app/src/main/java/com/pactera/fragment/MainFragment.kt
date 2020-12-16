package com.pactera.fragment

import android.os.Bundle
import android.view.View
import com.pactera.R
import com.pactera.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*


/**
 *  主页Fragment
 */
open class MainFragment : BaseFragment() {

    private var homeFragment : HomeFragment1? = null
    private var weiTaoFragment : WeiTaoFragment? = null
    private var demosFragment : DemosFragment? = null
    private var downloadFragment : DownloadFragment? = null

    var savedInstanceState: Bundle? = null

    val ORGANIZATION_FRAGMENT = "OrganizationFragmentTab"
    val RACE_FRAGMENT = "raceFragment"
    val SEARCH_PIGEON_FRAGMENT = "searchPigeonFragment"
    val ME_FRAGMENT = "meFragment"

    private val fragmentList = ArrayList<BaseFragment>(4)
    
    override fun initView(): View {
        return View.inflate(mActivity, R.layout.fragment_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentList(savedInstanceState)

        // 一开始选中HomeFragment
        switchFragment(homeFragment!!)

        fragment_main_radiobutton_home.setOnClickListener {
            switchFragment(homeFragment!!)
        }

//        fragment_main_radiobutton_race.setOnClickListener {
//            switchFragment(weiTaoFragment!!)
//        }

        fragment_main_radiobutton_search.setOnClickListener {
            switchFragment(demosFragment!!)
        }

        fragment_main_radiobutton_me.setOnClickListener {
            switchFragment(downloadFragment!!)
        }
    }

    fun initFragmentList(savedInstanceState: Bundle?){

        // 根据 savedInstanceState 的状态，看看是不是需要重新初始化 Fragment，防止fragment重叠
        if(savedInstanceState==null){

            if(homeFragment==null){
                homeFragment = HomeFragment1()
            }

            if(weiTaoFragment==null){
                weiTaoFragment = WeiTaoFragment()
            }

            if(demosFragment == null){
                demosFragment = DemosFragment()
            }

            if(downloadFragment == null){
                downloadFragment = DownloadFragment()
            }

            if(!homeFragment!!.isAdded){
                fragmentList.add(homeFragment!!)
                childFragmentManager.beginTransaction().add(R.id.fragment_main_framelayout, homeFragment!!, ORGANIZATION_FRAGMENT).commit()
            }

            if(!weiTaoFragment!!.isAdded){
                fragmentList.add(weiTaoFragment!!)
                childFragmentManager.beginTransaction().add(R.id.fragment_main_framelayout, weiTaoFragment!!, RACE_FRAGMENT).commit()
            }

            if(!demosFragment!!.isAdded){
                fragmentList.add(demosFragment!!)
                childFragmentManager.beginTransaction().add(R.id.fragment_main_framelayout, demosFragment!!, SEARCH_PIGEON_FRAGMENT).commit()
            }

            if(!downloadFragment!!.isAdded){
                fragmentList.add(downloadFragment!!)
                childFragmentManager.beginTransaction().add(R.id.fragment_main_framelayout, downloadFragment!!, ME_FRAGMENT).commit()
            }


        }else{

            if(homeFragment == null){
                homeFragment = childFragmentManager.findFragmentByTag(ORGANIZATION_FRAGMENT) as HomeFragment1
            }

            if(weiTaoFragment == null){
                weiTaoFragment = childFragmentManager.findFragmentByTag(RACE_FRAGMENT) as WeiTaoFragment
            }

            if(demosFragment == null){
                demosFragment = childFragmentManager.findFragmentByTag(SEARCH_PIGEON_FRAGMENT) as DemosFragment
            }

            if(downloadFragment == null){
                downloadFragment = childFragmentManager.findFragmentByTag(ME_FRAGMENT) as DownloadFragment
            }

            fragmentList.add(homeFragment!!)
            fragmentList.add(weiTaoFragment!!)
            fragmentList.add(demosFragment!!)
            fragmentList.add(downloadFragment!!)

        }

    }


    /**
     * switch home page fragment
     */
    fun switchFragment(toFragment: BaseFragment) {

        val manager = childFragmentManager
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

}
