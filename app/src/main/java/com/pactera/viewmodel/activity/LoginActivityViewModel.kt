package com.pactera.viewmodel.activity

import android.util.Log
import com.pactera.bean.Failure
import com.pactera.extension.fromJson
import com.pactera.viewmodel.base.BaseViewModel
import com.google.gson.Gson
import com.pactera.utils.MyOkHttpUtils
import com.pactera.utils.Urls
import java.util.HashMap

/**
 *  登录
 */
class LoginActivityViewModel: BaseViewModel(){

    private var rid: String = ""

//    var user = MutableLiveData<UserJson>()

    fun login(map : HashMap<String, String>){

        MyOkHttpUtils.postJson(Urls.LOGIN_URL, map) { res, ioe ->

            if(res!=null) {

                val responseGson = res.body()!!.string()

                if(res.isSuccessful){

//                    try {
//                        val userJson: UserJson = Gson().fromJson(responseGson)
//                        Log.d("正常数据：",userJson.toString())
//
//                        userJson.user?.let {
//                            userJson.user?.name?.let {
//                                SharedPreferencesUtils.putsetstring("name", userJson.user.name)
//                            }
//                            userJson.user?.username?.let {
//                                SharedPreferencesUtils.putsetstring("username", userJson.user.username)
//                            }
//                            userJson.user?.phone_no?.let {
//                                SharedPreferencesUtils.putsetstring("phone_no", userJson.user.phone_no)
//                            }
//
//                            SharedPreferencesUtils.putsetstring("role", userJson.user.role)
//
//                        }
//
//                        userJson.organization?.let {
//
//                            // organization_id 只在放飞员那边能用到
//                            userJson.organization?.id.let {
//                                SharedPreferencesUtils.putsetstring("organization_id", userJson.organization.id)
//                            }
//
//                            userJson.organization?.longitude.let {
//                                SharedPreferencesUtils.putsetstring("longitude", userJson.organization.longitude)
//                            }
//
//                            userJson.organization?.latitude.let {
//                                SharedPreferencesUtils.putsetstring("latitude", userJson.organization.latitude)
//                            }
//
//                        }
//
//                        SharedPreferencesUtils.putsetstring("token", userJson.token)
//
//                        SharedPreferencesUtils.putsetstring("user_id", userJson.id)
//
//                        user.postValue(userJson)
//
//                    }catch (e:Exception){
//                        println(e)
//                        user.postValue(null)
//                    }
                }else{
                    try {
                        val failure: Failure = Gson().fromJson(responseGson)
                        Log.d("非正常数据：",requestFailure.toString())
                        requestFailure.postValue(failure)

                    }catch (e:Exception){
                        println(e)
                        requestFailure.postValue(null)
                    }
                }


            }else {
//                user.postValue(null)
                realFailure.postValue(ioe)
            }

        }

    }


}
