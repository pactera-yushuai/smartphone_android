package com.pactera.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Goods : Serializable{

    @SerializedName("good_id")
    var goodId: String = ""

    @SerializedName("good_name")
    var goodName: String = ""

    @SerializedName("good_info")
    var goodInfo: String = ""

    @SerializedName("good_url_small")
    var goodUrlSmall: String = ""

    @SerializedName("good_url_big")
    var goodUrlBig: String = ""

    @SerializedName("good_price")
    var goodPrice: String = ""

    override fun toString(): String {
        return "Goods(goodId='$goodId', goodName='$goodName', goodInfo='$goodInfo', goodUrlSmall='$goodUrlSmall', goodUrlBig='$goodUrlBig', goodPrice='$goodPrice')"
    }

}
