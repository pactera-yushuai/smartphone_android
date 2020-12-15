package com.pactera.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.pactera.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun listGoesOverTheFold() {
        onView(withId(R.id.fragment_welcome_imageview)).check(matches(isDisplayed()))
    }
}
