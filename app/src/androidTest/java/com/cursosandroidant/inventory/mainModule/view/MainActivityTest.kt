package com.cursosandroidant.inventory.mainModule.view


import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun actionBar_menuItemClick_returnsMessage() {
        onView(withId(R.id.recyclerView)).perform(click())

        onView(withId(R.id.action_history)).perform(click())

        var snackbarMessage = ""
        mActivityScenarioRule.scenario.onActivity { activity ->
            snackbarMessage = activity.resources.getString(R.string.main_message_go_history)
        }

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackbarMessage)))
    }

    @Test
    fun contextMenu_menuItemClick_returnsMessage() {
        onView(withId(R.id.recyclerView)).perform(click())

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        var snackbarMessage = ""
        var chainToSearch = ""
        mActivityScenarioRule.scenario.onActivity { activity ->
            snackbarMessage = activity.resources.getString(R.string.main_message_go_exit)
            chainToSearch = activity.resources.getString(R.string.main_menu_title_exit)
        }

        onView(withText(chainToSearch)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackbarMessage)))
    }
}
