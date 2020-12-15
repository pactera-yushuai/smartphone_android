//package com.pactera.webservice
//
//import com.pactera.bean.AnnouncementArray
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Headers
//import retrofit2.http.QueryMap
//
///**
// *  公告
// */
//interface AnnouncementService {
//
//    @Headers("Content-Type: application/json")
//    @GET("post")
//    fun getAnnouncement(@QueryMap params: HashMap<String,String>): Call<AnnouncementArray>
//
//}