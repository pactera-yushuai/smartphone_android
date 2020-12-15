//package com.pactera.webservice
//
//import com.pactera.bean.UserFollowingArray
//import retrofit2.Call
//import retrofit2.http.*
//
//
///**
// *
// */
//interface UserFollowingService {
//
//    @Headers("Content-Type: application/json")
//    @GET("user/following")
//    fun getUserFollowing(@Header("Authorization") Authorization: String, @QueryMap params: HashMap<String,String>): Call<UserFollowingArray>
//
//}
