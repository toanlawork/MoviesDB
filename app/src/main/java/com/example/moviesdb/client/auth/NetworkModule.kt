package com.example.moviesdb.client.auth

import android.annotation.SuppressLint
import android.util.Log
import com.example.moviesdb.Constant
import com.example.moviesdb.NetworkConfig
import com.example.moviesdb.PackageUtil
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val jsonConfig = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (Constant.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                        .apply { level = HttpLoggingInterceptor.Level.BODY }
                    addInterceptor(loggingInterceptor)
                }
            }
            .build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(
        authPreference: AuthPreference,
        requestInterceptor: RequestInterceptor,
    ): Retrofit.Builder {
        val responseContentType = "application/json".toMediaType()
        val converterFactory = jsonConfig.asConverterFactory(responseContentType)

        val okHttpClient = getUnsafeOkHttpClient()
            ?.addInterceptor(requestInterceptor)
            ?.addInterceptor(
                requestInterceptor(
                    authPreference = authPreference,
                ),
            )?.build() ?: return Retrofit.Builder()

        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
    }
}
private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf(
            @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                    // Do nothing
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                    // Do nothing
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            },
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }
            .apply {
                if (Constant.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                        .apply { level = HttpLoggingInterceptor.Level.BODY }
                    addInterceptor(loggingInterceptor)
                }
            }
        builder
    } catch (e: Exception) {
        Log.e("", e.message.toString())
        throw RuntimeException(e)
    }

}

private fun requestInterceptor(authPreference: AuthPreference): Interceptor =
    Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder().method(original.method, original.body)

        // add header common
        requestBuilder.addHeader(NetworkConfig.PLATFORM_NAME, NetworkConfig.PLATFORM_MOBILE)
        requestBuilder.addHeader(NetworkConfig.CONTENT_TYPE, NetworkConfig.VALUE_CONTENT_TYPE)
        requestBuilder.addHeader(NetworkConfig.DEVICE_ID, PackageUtil.getDeviceId(authPreference))
        requestBuilder.addHeader(NetworkConfig.APP_VERSION, PackageUtil.getAppVersionCode())
        requestBuilder.addHeader(NetworkConfig.PLATFORM_VERSION, PackageUtil.getPlatformVersion())
        requestBuilder.addHeader(NetworkConfig.DEVICE_NAME, PackageUtil.getDeviceName())
        requestBuilder.addHeader(NetworkConfig.SOURCE_APP, NetworkConfig.VALUE_SOURCE_APP)

        // add access token
        authPreference.accessToken.let {
            Log.d("", "accessToken=$it")
            if (it.isNotBlank()) {
                requestBuilder.addHeader(
                    name = NetworkConfig.KEY_AUTHORIZATION,
                    value = "${NetworkConfig.AUTHORIZATION_PREFIX} $it",
                )
            }
        }

        val request = requestBuilder.build()
        return@Interceptor chain
            .withConnectTimeout(NetworkConfig.NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .withWriteTimeout(NetworkConfig.NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .withReadTimeout(NetworkConfig.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .proceed(request)
    }

