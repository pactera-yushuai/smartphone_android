package com.pactera.callback

import com.google.gson.internal.`$Gson$Types`

import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

import okhttp3.Call
import okhttp3.Response

/**
 *
 */
abstract class BaseCallBack<T> {

    var mType: Type? = null

    init {
        mType = getSuperclassTypeParameter(javaClass)
    }

    //    public abstract void onRequestBefore(Request request);

    fun onError(response: Response, code: Int, e: Exception) {
        println(response.toString())
        println(code)
        println(e)
    }

    abstract fun onFailure(call: Call, e: IOException)

    abstract fun onSuccess(call: Call, t: T)

    @Throws(IOException::class)
    abstract fun onResponse(response: Response, t: T)

    companion object {

        // 下面这段代码，将泛型 T 转换为 Type对象
        internal fun getSuperclassTypeParameter(subclass: Class<*>): Type? {
            val superclass = subclass.genericSuperclass
            if (superclass is Class<*>) {
                throw RuntimeException("Missing type parameter.")
            }
            val parameterized = superclass as ParameterizedType
            return `$Gson$Types`.canonicalize(parameterized.actualTypeArguments[0])
        }
    }


}
