package tj.rs.pharmacyonline.modules

import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tj.rs.pharmacyonline.network.Api
import tj.rs.pharmacyonline.network.ProfileApi
import tj.rs.pharmacyonline.network.SignupApi
import java.net.InetSocketAddress
import java.net.Proxy
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager

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

    private val proxyPort = 3128
    private val proxyHost = "10.10.10.96"
    private val username = "r.safarov"
    private val password = "android.11"

    private var proxyAuthenticator: Authenticator = object : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            val credential: String = Credentials.basic(username, password)
            return response.request.newBuilder()
                .header("Proxy-Authorization", credential)
                .build()
        }
    }

    private val normalClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()

    private var proxyClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress(proxyHost, proxyPort)))
        .proxyAuthenticator(proxyAuthenticator)
        .sslSocketFactory(getFakeSSL().socketFactory, TrustAllX509TrustManager())
        .build()

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss Z")
        .create()

    private val converterFactory = GsonConverterFactory.create(gson)

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(proxyClient)
        .build()

    companion object {
        const val BASE_URL = "https://test-android-files.000webhostapp.com/api.pharmacyonline.tj/"

        private val instance = NetworkService()

        fun instance(): Api = instance.retrofitBuilder.create(Api::class.java)

        fun signupInstance(): SignupApi = instance.retrofitBuilder.create(SignupApi::class.java)

        fun profileInstance(): ProfileApi = instance.retrofitBuilder.create(ProfileApi::class.java)
    }

    private fun getFakeSSL(): SSLContext {
        val sc = SSLContext.getInstance("TLS")
        sc.init(
            null,
            arrayOf<TrustManager>(TrustAllX509TrustManager()),
            SecureRandom()
        )
        return sc
    }

}
