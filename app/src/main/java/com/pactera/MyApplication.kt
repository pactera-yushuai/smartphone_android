package com.pactera

import android.app.Application
import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity

import com.pactera.utils.SharedPreferencesUtils

import kotlin.properties.Delegates


/**
 *
 */
open class MyApplication : Application() {


    // 这个是kotlin借助委托实现的单例，比下面的java式的简单
    companion object {
        var instance: MyApplication by Delegates.notNull()
        var organizationActivityHashMap = HashMap<String,String>()
        var pigeonActivityHashMap = HashMap<String,String>()
//        var checkedEquipment : Equipment? = null
        var activityList = ArrayList<AppCompatActivity>()

        fun clearAllStatic(){
            organizationActivityHashMap.clear()
            pigeonActivityHashMap.clear()
//            checkedEquipment = null
            activityList.clear()
        }

    }

//    companion object {
//        open var instance:MyApplication? = null
//        fun instance() = instance!!
//    }


    override fun onCreate() {

        super.onCreate()

        instance = this

        // 初始化 SharePreferences
        SharedPreferencesUtils.init(applicationContext, "userInfo")

    }


}
