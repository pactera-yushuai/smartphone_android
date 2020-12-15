package com.pactera.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class WeiTao1: Serializable{
    @SerializedName("shop_id")
    var shopId = ""
    @SerializedName("good_id")
    var goodId = ""
    @SerializedName("shop_name")
    var shopName = ""
    @SerializedName("shop_info")
    var shopInfo = ""
    @SerializedName("shop_url_small")
    var shopUrlSmall = ""
    @SerializedName("shop_url_big")
    var shopUrlBig = ""
    override fun toString(): String {
        return "WeiTao1(shopId='$shopId', goodId='$goodId', shopName='$shopName', shopInfo='$shopInfo', shopUrlSmall='$shopUrlSmall', shopUrlBig='$shopUrlBig')"
    }

}