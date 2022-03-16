package com.example.hubco.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.hubco.BuildConfig


/**
 * @AUTHOR Amandeep Singh
 * @date 15/06/2021
 */
object GeneralFunctions {


    /**
     * Function to hide keyboard from view
     */
    fun hideSoftKeyboard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    /**
     * Easy to notify in logcat
     */
    fun easyPrinter(message: String, className: String, obj: Any? = null) {
        if (BuildConfig.DEBUG) {
            println("Easy_Printer---------------------Start-------------------------------")
            if (obj != null) {
                println("Easy_Printer-$className----->$message<---->$obj")
            } else {
                println("Easy_Printer-$className---->$message")
            }
            println("Easy_Printer---------------------End---------------------------------")
        }
    }
}