package com.trackr.trackr_app.entities;

import entities.Person

import android.os.Build
import androidx.annotation.RequiresApi

import java.time.LocalDate
import java.time.Period

class EventFactory {
    /**
     * Returns true if the two dates are separated by a number of months present in the Fibionacci sequence
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun fibionaciMonths(target: LocalDate, compare: LocalDate) : Boolean {
        // https://en.wikipedia.org/wiki/Fibonacci_number#Identification
        fun isFibionacci(n: Int) : Boolean{
            fun isSquareRootable(i: Double): Boolean {
                return kotlin.math.sqrt(i) * kotlin.math.sqrt(i) == i
            }

            return isSquareRootable((5 * n * n + 4).toDouble()) || isSquareRootable((5 * n * n - 4).toDouble())
        }

        return isFibionacci(Period.between(compare, target).months)
    }

    fun createAnniversary(
        person: Person,
        date: LocalDate,
        customReminders: Set<LocalDate>?,
        reminderAlgorithm: ((LocalDate, LocalDate) -> Boolean)?
    ) = if (reminderAlgorithm == null)
        Event(Event.EventType.ANNIVERSARY, person, date, customReminders, ::fibionaciMonths) else
        Event(Event.EventType.ANNIVERSARY, person, date, customReminders, reminderAlgorithm)

    @RequiresApi(Build.VERSION_CODES.O)
    fun createBirthday(
        person: Person,
        date: LocalDate,
        customReminders: Set<LocalDate>?,
        reminderAlgorithm: ((LocalDate, LocalDate) -> Boolean)?
    ) = if (reminderAlgorithm == null)
        Event(
            Event.EventType.BIRTHDAY,
            person,
            date,
            customReminders
        ) { target: LocalDate, compare: LocalDate -> target.dayOfYear == compare.dayOfYear} else
        Event(Event.EventType.BIRTHDAY, person, date, customReminders, reminderAlgorithm)
}
