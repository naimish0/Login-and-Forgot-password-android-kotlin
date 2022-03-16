package com.example.hubco.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.hubco.R
import com.example.hubco.base.FragmentNavigationBuilder
import com.example.hubco.databinding.FragmentForgotPasswordBinding
import com.example.hubco.databinding.FragmentLoginBinding
import com.example.hubco.ui.AfterLoginFragment
import com.example.hubco.ui.AfterPasswordResetFragment
import com.example.hubco.ui.LoginActivity
import com.example.hubco.ui.login.LoginViewModel
import com.example.hubco.utility.getViewModelFactory
import com.example.hubco.utility.showToast


class ForgotPasswordFragment : Fragment(),ForgotPasswordViewActor{
    //region variable
    private val mviewModel by viewModels<ForgotPasswordViewModel> { getViewModelFactory() }
    private var _binding: FragmentForgotPasswordBinding? = null
    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviewModel.setViewActor(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.btnReset?.setOnClickListener {
            mviewModel.forgotPassword(_binding!!.tietMobileNumber.text.toString(),_binding!!.tietDob.text.toString())
        }
    }

    override fun afterPasswordReset() {
        (activity as LoginActivity).executeNavigation(
            FragmentNavigationBuilder(AfterPasswordResetFragment())
                .container(R.id.fl_container)
                .isAddFragment(false)
                .isBackStack(true)
                .bundle(null)
                .build()
        )
    }

    override fun onApiError(throwable: Throwable) {
        showToast(message = throwable.localizedMessage)
    }

    override fun onValidationError(resId: Int) {
        showToast(resId=resId)
    }

}