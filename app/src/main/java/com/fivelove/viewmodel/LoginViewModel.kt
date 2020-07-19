package com.fivelove.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult

/**
 * Created by Nguyen Kim Khanh on 7/13/2020.
 */
class LoginViewModel(application: Application, private val callback: FacebookCallback<LoginResult>) : AndroidViewModel(application) {
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    fun onClickBtnLoginFacebook(view: View) {

    }
}