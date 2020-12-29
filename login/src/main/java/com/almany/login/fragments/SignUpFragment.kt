package com.almany.login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almany.basemodule.base.BaseFragment
import com.almany.login.R


class SignUpFragment : BaseFragment() {
    override fun provideLayout() = R.layout.fragment_sign_up
    override fun provideFragmentTitle(): String? = resources.getString(R.string.registration)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}