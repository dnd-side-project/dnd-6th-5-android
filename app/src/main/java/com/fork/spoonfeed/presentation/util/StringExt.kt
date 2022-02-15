package com.fork.spoonfeed.presentation.util

fun getIntFromCharSequence(data: CharSequence?): Int? {
    return data?.let { if (it.isEmpty()) null else it.toString().toInt() }
}
