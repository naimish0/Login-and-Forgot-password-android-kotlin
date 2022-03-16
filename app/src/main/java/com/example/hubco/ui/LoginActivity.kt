package com.example.hubco.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hubco.R
import com.example.hubco.base.BaseActivity
import com.example.hubco.base.FragmentNavigationBuilder
import com.example.hubco.databinding.FragmentLoginBinding
import com.example.hubco.ui.login.LoginFragment

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeNavigation(
            FragmentNavigationBuilder(LoginFragment())
                .container(R.id.fl_container)
                .isAddFragment(false)
                .isBackStack(false)
                .bundle(null)
                .build()
        )
    }

}