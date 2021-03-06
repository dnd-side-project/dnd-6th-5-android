package com.fork.spoonfeed.domain.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

data class Age(
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null
) {
    fun isValid(): Boolean {
        if (year !in (1900..LocalDate.now().year)) return false

        val ageYear = year?.toString() ?: return false
        val ageMonth = month?.toString() ?: return false
        val ageDay = day?.toString() ?: return false

        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            dateFormat.isLenient = false
            dateFormat.parse("$ageYear-$ageMonth-$ageDay")
            true
        } catch (e: ParseException) {
            false
        }
    }

    // yyyyMMdd
    fun formatAge(): String? {
        val formattedYear = year?.toString() ?: return null
        val formattedMonth = month?.let {
            if (it > 10) it.toString() else "0$it"
        } ?: return null
        val formattedDay = day?.let {
            if (it > 10) it.toString() else "0$it"
        } ?: return null

        return formattedYear + formattedMonth + formattedDay
    }
}
