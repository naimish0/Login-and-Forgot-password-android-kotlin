package com.example.hubco.base

import androidx.annotation.StringRes


/**
 * @AUTHOR Amandeep Singh
 * @date 15/06/2021
 * */
interface CommonViewActor : BaseViewActor {
    /**
     *Method to show API Error/Internet Error on view
     * @param throwable containing error
     */
    fun onApiError(throwable: Throwable)
    /**
     * show validation error on view
     *
     * @param resId string resource id
     */
    fun onValidationError(@StringRes resId: Int)
    /**
     * Hide keyboard
     *
     */
    fun hideKeyboard(){}// GeneralFunctions.hideSoftKeyboard(requireContext(), mBinding!!.root)
}