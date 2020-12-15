package com.pactera.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WeiTao2 : Serializable {

    @SerializedName("shop_id")
    var shopId = ""
    @SerializedName("shop_name")
    var shopName = ""
    @SerializedName("shop_info")
    var shopInfo = ""
    @SerializedName("shop_url_small")
    var shopUrlSmall = ""
    @SerializedName("shop_url_big")
    var shopUrlBig = ""
    @SerializedName("shop_flg")
    var shopFlg = ""
    @SerializedName("good_id")
    var goodId = ""
    @SerializedName("good_name")
    var goodName = ""
    @SerializedName("good_info")
    var goodInfo = ""
    @SerializedName("good_url_small")
    var goodUrlSmall = ""
    @SerializedName("good_url_big")
    var goodUrlBig = ""
    @SerializedName("good_price")
    var goodPrice = ""
    @SerializedName("good_sales_flg")
    var goodSalesFlg = ""

    override fun toString(): String {
        return "WeiTao2(shopId='$shopId', shopName='$shopName', shopInfo='$shopInfo', shopUrlSmall='$shopUrlSmall', shopUrlBig='$shopUrlBig', shopFlg='$shopFlg', goodId='$goodId', goodName='$goodName', goodInfo='$goodInfo', goodUrlSmall='$goodUrlSmall', goodUrlBig='$goodUrlBig', goodPrice='$goodPrice', goodSalesFlg='$goodSalesFlg')"
    }

}
