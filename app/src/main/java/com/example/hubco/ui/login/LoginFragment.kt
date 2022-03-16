package com.example.hubco.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hubco.R
import com.example.hubco.base.FragmentNavigationBuilder
import com.example.hubco.databinding.FragmentLoginBinding
import com.example.hubco.ui.AfterLoginFragment
import com.example.hubco.ui.LoginActivity
import com.example.hubco.ui.forgotpassword.ForgotPasswordFragment
import com.example.hubco.ui.forgotpassword.ForgotPasswordViewActor
import com.example.hubco.utility.getViewModelFactory
import com.example.hubco.utility.showToast


class LoginFragment : Fragment(), LoginViewActor {
    //region variable
    private val mviewModel by viewModels<LoginViewModel> { getViewModelFactory() }
    private var loginBinding: FragmentLoginBinding? = null
    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviewModel.setViewActor(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return loginBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding?.btnSignIn?.setOnClickListener {
            mviewModel.doLogin(loginBinding!!.tietUsername.text.toString(),loginBinding!!.tietPassword.text.toString())
        }
        loginBinding?.tvForgotPassword?.setOnClickListener {
            (activity as LoginActivity).executeNavigation(
                FragmentNavigationBuilder(ForgotPasswordFragment())
                    .container(R.id.fl_container)
                    .isAddFragment(false)
                    .isBackStack(true)
                    .bundle(null)
                    .build()
            )
        }
    }

    override fun forgotPassword() {
        (activity as LoginActivity).executeNavigation(
            FragmentNavigationBuilder(ForgotPasswordFragment())
                .container(R.id.fl_container)
                .isAddFragment(false)
                .isBackStack(true)
                .bundle(null)
                .build()
        )
    }

    override fun afterLoggedIn() {
        (activity as LoginActivity).executeNavigation(
            FragmentNavigationBuilder(AfterLoginFragment())
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
        showToast(resId = resId)
    }

}