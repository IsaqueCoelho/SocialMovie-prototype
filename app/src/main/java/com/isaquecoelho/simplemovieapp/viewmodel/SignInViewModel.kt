package com.isaquecoelho.simplemovieapp.viewmodel

import android.os.Handler
import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.isaquecoelho.simplemovieapp.util.StringUtils

class SignInViewModel: ViewModel() {

    private val LOG_TAG = "SignInViewModel"

    private val USER_EMAIL = "isaque@gmail.com"
    private val USER_PASSWORD = "7908281B119B087A0C719CD42D7D75A9F301304F4DB96B03756001317DBD942F"

    private lateinit var mStringUtils: StringUtils

    val email: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()

    val formLoading: ObservableBoolean = ObservableBoolean()
    val userAuthorized: ObservableBoolean = ObservableBoolean()

    fun onSignInClick(){

        formLoading.set( true )

        if(valideteFields()){
            Handler().postDelayed( { checkUser() }, 2000 )
        } else{
            formLoading.set( false )
            userAuthorized.set( false )
        }
    }

    private fun valideteFields(): Boolean {

        if ( email.get() == null || password.get() == null ){
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()){
            return false
        }

        if(TextUtils.isEmpty(password.get()) || password.get()?.length!! < 4){
            return false
        }

        return true
    }

    private fun checkUser() {

        mStringUtils = StringUtils()

        if( email.get().equals(USER_EMAIL, ignoreCase = true) &&
            mStringUtils.hashToSha256( password.get().toString() ) == USER_PASSWORD ){

            userAuthorized.set( true )
        }

        formLoading.set( false )
    }
}