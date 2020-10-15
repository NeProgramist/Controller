package com.agb.controller.framework.datasource.remote.api

import com.agb.controller.framework.App
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator: Authenticator {
    /*
        this method will be automatically called when any method returns 401 error
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = getNewToken()
        return token?.let {
            response
                .request()
                .newBuilder()
                .header("Authorization", it)
                .build()
        }
    }


    private fun getNewToken(): String? = with(App.preferencesManager) {
        /*
            I think there is no possibility(on the start of the program I check whether this params
            exists in sharedPrefs) that nickname or password will be null, but sharedPrefs
            always return string with nullable type so I create this exceptions just to make them
            non-nullable(and if I wrong I will get some error and resolve it)
         */
            val nickname = getString("nickname") ?: throw IllegalArgumentException("no name")
            val password = getString("password") ?: throw IllegalArgumentException("no pass")
            val call = AuthApi().auth(username = nickname, password = password)

           call.execute().body()?.token
        }
}