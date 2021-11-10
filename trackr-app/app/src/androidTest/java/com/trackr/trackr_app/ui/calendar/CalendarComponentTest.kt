package com.trackr.trackr_app.ui.calendar

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.trackr.trackr_app.MainActivity
import com.trackr.trackr_app.calendar.Calendar
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate


class CalendarComponentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun selectFirstDayOfMonthTest() {
        var selectedDate = LocalDate.of(2021, 4, 20)
        composeTestRule.setContent {
            Calendar(
                selectedDate,
                {selectedDate = selectedDate.withDayOfMonth(it)},
                {selectedDate = selectedDate.plusMonths(it)}
            )
        }

        composeTestRule.onNodeWithText("1").performClick()
        assertEquals(1, selectedDate.dayOfMonth)
    }

    @Test
    fun selectLastDayOfMonthTest() {
        var selectedDate = LocalDate.of(2021, 4, 20)
        composeTestRule.setContent {
            Calendar(
                selectedDate,
                {selectedDate = selectedDate.withDayOfMonth(it)},
                {selectedDate = selectedDate.plusMonths(it)}
            )
        }

        composeTestRule.onNodeWithText(selectedDate.lengthOfMonth().toString()).performClick()
        assertEquals(selectedDate.lengthOfMonth(), selectedDate.dayOfMonth)
    }

    @Test
    fun selectNonExistentDayTest() {
        var selectedDate = LocalDate.of(2021, 4, 20)
        composeTestRule.setContent {
            Calendar(
                selectedDate,
                {selectedDate = selectedDate.withDayOfMonth(it)},
                {selectedDate = selectedDate.plusMonths(it)}
            )
        }

        // we only test the first non-existent day because the rest are identical
        composeTestRule.onAllNodesWithText("").onFirst().performClick()
        assertEquals(20, selectedDate.dayOfMonth)  // there should be no change
    }

    @Test
    fun increaseMonthTest() {
        var selectedDate = LocalDate.of(2021, 4, 20)
        composeTestRule.setContent {
            Calendar(
                selectedDate,
                {selectedDate = selectedDate.withDayOfMonth(it)},
                {selectedDate = selectedDate.plusMonths(it)}
            )
        }

        composeTestRule.onNodeWithContentDescription("Next Month").performClick()
        assertEquals(5, selectedDate.month.value)
    }

    @Test
    fun decreaseMonthTest() {
        var selectedDate = LocalDate.of(2021, 4, 20)
        composeTestRule.setContent {
            Calendar(
                selectedDate,
                {selectedDate = selectedDate.withDayOfMonth(it)},
                {selectedDate = selectedDate.plusMonths(it)}
            )
        }

        composeTestRule.onNodeWithContentDescription("Previous Month").performClick()
        assertEquals(3, selectedDate.month.value)
    }
}
