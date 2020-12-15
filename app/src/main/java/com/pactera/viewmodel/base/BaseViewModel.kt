package com.pactera.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pactera.bean.Failure
import com.pactera.bean.Success
import com.pactera.utils.SharedPreferencesUtils
import java.io.IOException


open class BaseViewModel: ViewModel() {

    val token = SharedPreferencesUtils.getstring("token", "")

    open val bearerToken = "bearer $token"

    var requestFailure = MutableLiveData<Failure>()

    var requestSuccess = MutableLiveData<Success>()

    var realFailure = MutableLiveData<IOException>()






}

