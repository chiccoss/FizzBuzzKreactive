package com.kreactive.fizzbuzzkreactive.app.extention

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


fun Fragment.showShortSnackbar(message : String)  {
    Snackbar.make(requireView(),message,Snackbar.LENGTH_SHORT).show()
}