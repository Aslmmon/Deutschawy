package com.almany.basemodule.base

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    lateinit var loadingDialog: ProgressDialog
    protected lateinit var activity: Activity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog = ProgressDialog(activity)
        (activity as AppCompatActivity?)?.supportActionBar?.title = provideFragmentTitle()
        return inflater.inflate(provideLayout(), container, false)

    }


    abstract fun provideLayout(): Int

    abstract fun provideFragmentTitle(): String?

    fun showProgress() {
        loadingDialog.show()
    }

    fun dismissProgressDialog() {
        loadingDialog.dismiss()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = activity
    }

}