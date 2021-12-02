package com.example.ollineshoppingclone.api

import com.example.ollineshopping.utils.Constatn
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkingMenejer {

    var retrofit : Retrofit? = null
    var api : Api? = null

    fun getApiServer() : Api {
        if (api == null){
           retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constatn.BASE_URL)
                .build()
            api = retrofit!!.create(Api::class.java)
        }
      return api!!
    }
}