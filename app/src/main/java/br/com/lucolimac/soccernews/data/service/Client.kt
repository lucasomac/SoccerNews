package br.com.lucolimac.soccernews.data.service

import br.com.lucolimac.soccernews.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Client {
    companion object {
        fun getService(): SoccerNewsService {
            val okHttpClient = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(SoccerNewsService::class.java)
        }
    }
}