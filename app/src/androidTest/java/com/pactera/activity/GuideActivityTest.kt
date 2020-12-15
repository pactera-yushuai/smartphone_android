package com.pactera.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.pactera.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class GuideActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(GuideActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun testBanner(){
        Espresso.onView(ViewMatchers.withId(R.id.fragment_guide_banner)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun tearDown() {
    }
}