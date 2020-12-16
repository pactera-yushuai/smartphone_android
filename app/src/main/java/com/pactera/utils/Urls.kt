package com.pactera.utils

import com.pactera.BuildConfig


/**
 * Decription: 接口地址列表
 */

object Urls {

    const val SERVER_URL = BuildConfig.BASE_URL

    const val IMAGE_URL = "http://192.168.103.32:8080/pigeon/upload/"

    const val GO_SERVER_URL = ""

    const val LOGIN_URL = "$SERVER_URL/auth" // 登录并获取token

    const val HOME_URL = "$SERVER_URL/getTopList"

    const val SECOND_URL = "$SERVER_URL/getShopList"

    const val DOWNLOAD_URL = "https://gmxjjzapi.dkvet.com/pactera.mp4" //

}
