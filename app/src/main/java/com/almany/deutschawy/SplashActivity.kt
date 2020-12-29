package com.almany.deutschawy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.almany.basemodule.common.Constants.SPLASH_DUARTION
import com.almany.basemodule.common.Navigation
import com.almany.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DUARTION)
//            val settings = getSharedPreferences("prefs", 0)
//            val passed = settings.getBoolean("firstRun", false)
            //if (passed) {
               // Navigation.goToLoginActiviy(this@SplashActivity)
            startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            //} else {
            //    Navigation.goToIntroActivity(this@SplashActivity)
           // }
        }
    }
}