package com.fork.spoonfeed.presentation.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import androidx.annotation.IdRes

@SuppressLint("ResourceType")
fun Context.showFloatingDialog(@IdRes layout: Int): Dialog {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(layout)
    dialog.setCancelable(false)

    dialog.getWindow()!!.setGravity(Gravity.CENTER)
    dialog.show()
    return dialog
}