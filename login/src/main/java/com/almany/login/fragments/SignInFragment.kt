package com.almany.login.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.almany.basemodule.base.BaseFragment
import com.almany.login.R
import com.google.android.material.button.MaterialButton


class SignInFragment : BaseFragment() {
    override fun provideLayout() = R.layout.fragment_sign_in
    override fun provideFragmentTitle(): String?  = resources.getString(R.string.sign_in_title)

    private lateinit var binding: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= activity.findViewById<MaterialButton>(R.id.btn_signIn)
        binding.setOnClickListener {
            Toast.makeText(activity, "Hyyy", Toast.LENGTH_SHORT).show()
        }
    }
}