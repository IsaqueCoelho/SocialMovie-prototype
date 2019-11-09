package com.isaquecoelho.simplemovieapp.ui.signIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProviders
import com.isaquecoelho.simplemovieapp.R
import com.isaquecoelho.simplemovieapp.databinding.ActivitySigninBinding
import com.isaquecoelho.simplemovieapp.ui.main.MainActivity
import com.isaquecoelho.simplemovieapp.viewmodel.SignInViewModel

class SignInActivity : AppCompatActivity() {

    private lateinit var mSignInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSignInViewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        val biding =
            DataBindingUtil.setContentView<ActivitySigninBinding>(this, R.layout.activity_signin)
        biding.viewmodel = mSignInViewModel
        biding.setLifecycleOwner(this)

        setContentView(biding.root)
    }

    override fun onStart() {
        super.onStart()
        implementingObservers()
    }

    private fun implementingObservers() {

        mSignInViewModel.userAuthorized.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if ( mSignInViewModel.userAuthorized.get() ){
                    startActivity( Intent( this@SignInActivity, MainActivity::class.java ) )
                    finish()
                }
            }
        })
    }
}
