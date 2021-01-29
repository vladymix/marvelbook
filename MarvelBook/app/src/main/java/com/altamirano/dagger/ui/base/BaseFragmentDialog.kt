package com.altamirano.dagger.ui.base

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseFragmentDialog : BottomSheetDialogFragment() {

    private lateinit var mActivity: BaseActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.mActivity = activity as BaseActivity
    }

    fun showLoading() {
        mActivity.showLoading()
    }

    fun hideLoading() {
        mActivity.hideLoading()
    }

}