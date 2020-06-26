package tj.rs.pharmacyonline.modules

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tj.rs.pharmacyonline.network.Api
import tj.rs.pharmacyonline.network.ProfileApi
import tj.rs.pharmacyonline.network.SignupApi

/**
 * Created by Rustam Safarov (RS) on 13.04.2020.
 * (c) 2020 RS DevTeam. All rights reserved!
 */
class NetworkService private constructor() {

    private val loggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val baseInterceptor: Interceptor = Interceptor.invoke { chain ->
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(newUrl)
            .build()

        return@invoke chain.proceed(request)
    }

    private val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss Z")
        .create()

    private val converterFactory = GsonConverterFactory.create(gson)

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()
        .create(Api::class.java)

    private val signupService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()
        .create(SignupApi::class.java)

    private val profileService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()
        .create(ProfileApi::class.java)

    companion object {
        const val BASE_URL = "https://test-android-files.000webhostapp.com/api.pharmacyonline.tj/"

        private val instance = NetworkService()

        fun instance(): Api = instance.service

        fun signupInstance(): SignupApi = instance.signupService

        fun profileInstance(): ProfileApi = instance.profileService
    }

}
