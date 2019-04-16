package com.ace.aleksandr.searchuserongithub.data.api

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://api.github.com"

object ApiHolder {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(GitApiInterface::class.java)
}