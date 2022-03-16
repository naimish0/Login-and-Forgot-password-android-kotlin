package com.example.hubco.utility

/**
 * @AUTHOR Naimish Gupta
 * @DATE   05/01/2022
 */
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.saveo.base.HubcoApplication
import com.example.saveo.base.ViewModelFactory


fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = HubcoApplication.apiCallMethods
    return ViewModelFactory(repository)
}

fun Fragment.showToast(resId: Int? = null, message: String? = null) {
    activity?.let {
        Toast.makeText(
            it, if (resId != null) {
                it.getString(resId)
            } else {
                message!!
            }, Toast.LENGTH_SHORT
        ).show()
    }
}
