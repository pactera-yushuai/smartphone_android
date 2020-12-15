package com.pactera.common

object Dict {

    val equipmentMap = HashMap<String,String>()

    val colorMap = HashMap<String,String>()

    val sexMap = HashMap<String,String>()

    val eyeMap = HashMap<String,String>()

    val raceStatusMap = HashMap<String,String>()
    
    val provinceMap = HashMap<String,String>()

    val raceTypeMap = HashMap<String,String>()

    init {

        equipmentMap["电子扫描鸽钟"] = "clock"
        equipmentMap["手持扫描设备"] = "handheld_scanner"

        colorMap["白"] = "white"
        colorMap["灰"] = "blue"
        colorMap["黑"] = "black"
        colorMap["红（绛）"] = "red"
        colorMap["花"] = "grizzle"
        colorMap["红楞"] = "red_barred"
        colorMap["深灰"] = "dark_blue"
        colorMap["银白"] = "sliver"
        colorMap["雨点"] = "checker"
        colorMap["灰雨点"] = "blue_checker"
        colorMap["深雨点"] = "dark_checker"
        colorMap["灰白条"] = "blue_white_flight"
        colorMap["雨白条"] = "checker_white_flight"
        colorMap["白条"] = "red_white_flight"
        colorMap["黑白条"] = "black_white_flight"
        colorMap["麒麟花"] = "mosaic"


        sexMap["雄"] = "male"
        sexMap["雌"] = "female"

        eyeMap["黄"] = "yellow"
        eyeMap["砂"] = "sand"
        eyeMap["牛"] = "bull"


        raceStatusMap["刚创建"] = "created"
        raceStatusMap["报名中"] = "issued"
        raceStatusMap["集鸽中"] = "shipping"
        raceStatusMap["集鸽结束"] = "shipping_finished"
        raceStatusMap["比赛推迟"] = "race_delayed"
        raceStatusMap["比赛中"] = "racing"
        raceStatusMap["比赛结束"] = "finished"
        raceStatusMap["比赛中止"] = "terminated"


        provinceMap["上海市"] = "31"
        provinceMap["云南省"] = "53"
        provinceMap["内蒙古自治区"] = "15"
        provinceMap["北京市"] = "11"
        provinceMap["吉林省"] = "22"
        provinceMap["四川省"] = "51"
        provinceMap["天津市"] = "12"
        provinceMap["宁夏回族自治区"] = "64"
        provinceMap["安徽省"] = "34"
        provinceMap["山东省"] = "37"
        provinceMap["山西省"] = "14"
        provinceMap["广东省"] = "44"
        provinceMap["广西壮族自治区"] = "45"
        provinceMap["新疆维吾尔自治区"] = "65"
        provinceMap["江苏省"] = "32"
        provinceMap["江西省"] = "36"
        provinceMap["河北省"] = "13"
        provinceMap["河南省"] = "41"
        provinceMap["浙江省"] = "33"
        provinceMap["海南省"] = "46"
        provinceMap["湖北省"] = "42"
        provinceMap["湖南省"] = "43"
        provinceMap["甘肃省"] = "62"
        provinceMap["福建省"] = "35"
        provinceMap["西藏自治区"] = "54"
        provinceMap["贵州省"] = "52"
        provinceMap["辽宁省"] = "21"
        provinceMap["重庆市"] = "50"
        provinceMap["陕西省"] = "61"
        provinceMap["青海省"] = "63"
        provinceMap["黑龙江省"] = "23"


        raceTypeMap["竞翔赛"] = "racing"
        raceTypeMap["多关赛"] = "multi_round"
        raceTypeMap["单关赛"] = "multi_round_sub"
        raceTypeMap["血战到底"] = "battle_fight"
        raceTypeMap["血战到底关赛"] = "battle_fight_sub"

    }

}