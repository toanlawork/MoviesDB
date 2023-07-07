package com.example.moviesdb.client.auth

import android.content.SharedPreferences
import javax.inject.Inject

interface AuthPreference {
    var accessToken: String

    var lastLoggedInUsername: String

    var lastLoggedInFullName: String

    var lastLoggedInCompanyName: String

    var lastPhoneNumber: String

    var lastAuthMethod: String

    var lastLoggedInAvatar: String

    val hasSession: Boolean

    var deviceId: String

    suspend fun deleteAllPreference()

    var lastLoginErrorCode: String

    suspend fun deleteLoginErrorPreference()

    var userId: String

    var isShowNotification: Boolean

    var firebaseToken: String
}

class AuthPreferenceImpl @Inject constructor(
    @SecurePreference private val encryptedPrefs: SharedPreferences
) : AuthPreference {
    companion object {
        private const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"

        private const val LAST_LOGGED_IN_USERNAME_KEY = "LAST_LOGGED_IN_USERNAME_KEY"
        private const val LAST_LOGGED_IN_FULL_NAME_KEY = "LAST_LOGGED_IN_FULL_NAME_KEY"
        private const val LAST_LOGGED_IN_COMPANY_NAME_KEY = "LAST_LOGGED_IN_COMPANY_NAME_KEY"
        private const val LAST_LOGGED_IN_AVATAR_KEY = "LAST_LOGGED_IN_AVATAR_KEY"
        private const val LAST_LOGGED_PHONE_NUMBER = "LAST_LOGGED_PHONE_NUMBER"
        private const val LAST_LOGGED_AUTH_METHOD = "LAST_LOGGED_AUTH_METHOD"
        private const val USER_ID_KEY = "USER_ID_KEY"
        private const val DEVICE_ID = "DEVICE_ID"
        private const val LAST_LOGGED_IN_ERROR_KEY = "LAST_LOGGED_IN_ERROR_KEY"
        private const val SHOW_NOTIFICATION_KEY = "SHOW_NOTIFICATION_KEY"
        private const val FIREBASE_TOKEN_KEY = "FIREBASE_TOKEN_KEY"
    }

    override var accessToken: String by StringPreferenceDelegate(encryptedPrefs, ACCESS_TOKEN_KEY)

    override var userId: String by StringPreferenceDelegate(encryptedPrefs, USER_ID_KEY)

    override var lastLoggedInUsername: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_IN_USERNAME_KEY
    )

    override var lastLoggedInFullName: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_IN_FULL_NAME_KEY
    )

    override var lastLoggedInCompanyName: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_IN_COMPANY_NAME_KEY
    )
    override var lastPhoneNumber: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_PHONE_NUMBER
    )
    override var lastAuthMethod: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_AUTH_METHOD
    )

    override var lastLoggedInAvatar: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_IN_AVATAR_KEY
    )

    override var lastLoginErrorCode: String by StringPreferenceDelegate(
        encryptedPrefs,
        LAST_LOGGED_IN_ERROR_KEY
    )

    override var firebaseToken: String by StringPreferenceDelegate(
        encryptedPrefs,
        FIREBASE_TOKEN_KEY
    )

    override val hasSession: Boolean by KeyExistPreferenceDelegate(encryptedPrefs, ACCESS_TOKEN_KEY)

    override var isShowNotification: Boolean by BooleanPreferenceDelegate(
        encryptedPrefs,
        SHOW_NOTIFICATION_KEY
    )

    override var deviceId: String by StringPreferenceDelegate(
        encryptedPrefs,
        DEVICE_ID
    )

    override suspend fun deleteAllPreference() {
        encryptedPrefs.removeData(LAST_LOGGED_IN_USERNAME_KEY)
        encryptedPrefs.removeData(LAST_LOGGED_IN_FULL_NAME_KEY)
        encryptedPrefs.removeData(LAST_LOGGED_IN_COMPANY_NAME_KEY)
        encryptedPrefs.removeData(LAST_LOGGED_IN_AVATAR_KEY)
        encryptedPrefs.removeData(SHOW_NOTIFICATION_KEY)
    }

    override suspend fun deleteLoginErrorPreference() {
        encryptedPrefs.removeData(LAST_LOGGED_IN_ERROR_KEY)
    }
}
