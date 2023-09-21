package com.kreactive.fizzbuzzkreactive.app.extention

import android.content.Context
import android.widget.Toast

fun Context.showLongToast(message: Int) = this.showLongToast(getString(message))

fun Context.showLongToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.showShortToast(message: Int) = this.showShortToast(getString(message))

fun Context.showShortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
