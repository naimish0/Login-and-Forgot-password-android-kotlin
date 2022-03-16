package com.example.hubco.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hubco.R


/**
 * @AUTHOR Naimish Gupta
 * @date /01/2022
 * */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * Execute navigation
     *
     * Execute fragment navigation based on [fragmentNavigationBuilder] options.
     * [fragmentNavigationBuilder] contains several options like fragment name,
     * Container name, add or replace fragment.
     *
     * @param fragmentNavigationBuilder
     */
    fun executeNavigation(fragmentNavigationBuilder: FragmentNavigationBuilder) {
        val currentFragment: Fragment = fragmentNavigationBuilder.fragment
        val fts = supportFragmentManager.beginTransaction()
        if (fragmentNavigationBuilder.isAnimation) {
            fts.setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
        }
        currentFragment.arguments = fragmentNavigationBuilder.bundle
        if (fragmentNavigationBuilder.isAddFragment)
            fts.add(
                fragmentNavigationBuilder.container!!,
                currentFragment,
                currentFragment.javaClass.simpleName
            )
        else
            fts.replace(
                fragmentNavigationBuilder.container!!,
                currentFragment,
                currentFragment.javaClass.simpleName
            )

        if (fragmentNavigationBuilder.isBackStack)
            fts.addToBackStack(currentFragment.javaClass.simpleName)
        fts.commit()
    }
}

data class FragmentNavigationBuilder(
    var fragment: Fragment,
    var container: Int? = null,
    var isAddFragment: Boolean = false,
    var isBackStack: Boolean = false,
    var isAnimation: Boolean = false,
    var bundle: Bundle? = null
) {
    fun container(container: Int) = apply { this.container = container }
    fun isAddFragment(isAddFragment: Boolean) = apply { this.isAddFragment = isAddFragment }
    fun isBackStack(isBackStack: Boolean) = apply { this.isBackStack = isBackStack }
    fun isAnimation(isAnimation: Boolean) = apply { this.isAnimation = isAnimation }
    fun bundle(bundle: Bundle?) = apply { this.bundle = bundle }
    fun build() = FragmentNavigationBuilder(
        fragment,
        container,
        isAddFragment,
        isBackStack,
        isAnimation,
        bundle
    )
}



