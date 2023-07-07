package com.example.moviesdb

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.example.moviesdb.client.auth.AuthPreference
import java.lang.Boolean
import java.util.UUID

object Constant {
    const val EMPTY_STRING = ""
    const val MAX_LENGTH = 100
    const val INT_ZERO = 0
    const val INT_FIVE = 5
    const val HOME = "home"
    const val SEARCH = "search"
    const val WATCH_LIST = "watch_list"
    const val MOVIE_DETAIL = "movie_detail"
    const val MOVIE_ID = "movieId"
    const val BASE_URL ="https://api.themoviedb.org/3/"
    const val POSTER_BASE_URL ="https://image.tmdb.org/t/p/w342"
    const val API_KEY = "6062a3a35dbee6277882be1d41bbccc3"
    const val DEBUG = true

}


object ApiResponse {
    const val HTTP_SERVER_ERROR = 500
    const val HTTP_UNAUTHORIZED = 401
    const val HTTP_TIMEOUT = 504
    const val HTTP_UNAVAILABLE = 503
    const val HTTP_FORBIDDEN = 403
}

object NetworkConfig {

    // Header key
    const val KEY_AUTHORIZATION = "Authorization"

    const val AUTHORIZATION_PREFIX = "Bearer"

    const val DEVICE_ID = "DEVICE-ID"

    const val APP_VERSION = "APP-VERSION"

    const val PLATFORM_VERSION = "PLATFORM-VERSION"

    const val DEVICE_NAME = "DEVICE-NAME"

    const val PLATFORM_NAME = "PLATFORM-NAME"

    const val PLATFORM_MOBILE = "ANDROID"

    const val CONTENT_TYPE = "Content-Type"

    const val VALUE_CONTENT_TYPE = "application/json"

    const val SOURCE_APP = "SOURCE-APP"

    const val VALUE_SOURCE_APP = "DCB"

    // network timeout
    const val NETWORK_CONNECT_TIMEOUT = 90

    const val NETWORK_WRITE_TIMEOUT = 90

    const val NETWORK_READ_TIMEOUT = 90

    const val CALL_TIMEOUT: Long = 40_000

    const val CONNECT_TIMEOUT: Long = 40_000

    const val READ_TIMEOUT: Long = 40_000

    const val WRITE_TIMEOUT: Long = 40_000
}

object PackageUtil {

    /**
     * This method using to get version code of Application
     *
     * @param context [Context]
     * return version app
     */
    fun getVersionApp(context: Context): String {
        return try {
            val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("", e.message.toString())
            Constant.EMPTY_STRING
        }
    }

    /**
     * This method using to get device id
     *
     * return device id
     */
    fun getDeviceId(authPreference: AuthPreference): String {
        val deviceId = authPreference.deviceId
        return deviceId.ifEmpty {
            val id = UUID.randomUUID().toString()
            authPreference.deviceId = id
            id
        }
    }

    /**
     * This method using to app version code
     *
     * return version code
     */
    fun getAppVersionCode(): String {
        return "1"
    }

    /**
     * This method using to platform app
     *
     * return device platform
     */
    fun getPlatformVersion(): String {
        return "1.0"
    }

    fun getDeviceName(): String {
        val manufacturer: String = Build.MANUFACTURER
        val model: String = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }

    private fun capitalize(characters: String?): String {
        if (characters == null || characters.isEmpty()) {
            return Constant.EMPTY_STRING
        }
        val first = characters[0]
        return if (Character.isUpperCase(first)) {
            characters
        } else {
            Character.toUpperCase(first).toString() + characters.substring(1)
        }
    }
}


object SharedPreferenceConfig {
    const val SECURE_SHARED_PREFERENCES_FILE_NAME = "SECURE_PREFERENCE_FILE"

    const val SHARED_PREFERENCES_FILE_NAME = "PREFERENCE_FILE"
}