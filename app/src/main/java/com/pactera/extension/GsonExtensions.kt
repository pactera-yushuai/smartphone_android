package com.pactera.extension

import com.google.gson.Gson

// 在 kotlin中扩展 Java 类的 Gson.fromJson 方法
// 不在传入 T 的class，inline 的作用就是将函数插入到被调用处，配合 reified 就可以获取到 T 真正的类型
inline fun <reified T : Any> Gson.fromJson(json: String): T {
    return Gson().fromJson(json, T::class.java)
}