package com.example.hubco.ui.login

import com.example.hubco.base.BaseViewActor
import com.example.hubco.base.CommonViewActor

interface LoginViewActor :CommonViewActor{

    /**
     * Move user to forgot password screen
     *
     */
    fun forgotPassword()

    /**
     *
     */

    fun afterLoggedIn()
}