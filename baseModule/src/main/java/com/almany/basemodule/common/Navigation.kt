package com.almany.basemodule.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.almany.login.LoginActivity

object Navigation {
    fun goToLoginActiviy(ctx:Context){
        (ctx as Activity).startActivity(Intent(ctx,LoginActivity::class.java))
    }
}