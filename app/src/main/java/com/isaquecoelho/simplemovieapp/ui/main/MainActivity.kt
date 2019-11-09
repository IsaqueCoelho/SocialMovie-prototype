package com.isaquecoelho.simplemovieapp.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.isaquecoelho.simplemovieapp.R
import com.isaquecoelho.simplemovieapp.ui.main.catalog.CatalogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.framelayout_container,
                    CatalogFragment.newInstance()
                )
                .commitNow()
        }
    }

}
