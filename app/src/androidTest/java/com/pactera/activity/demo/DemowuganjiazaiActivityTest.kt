package com.pactera.activity.demo

import androidx.fragment.app.FragmentManager
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.pactera.R
import com.pactera.activity.HomeActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DemowuganjiazaiActivityTest{

    @get:Rule
    val activityRule = ActivityTestRule(DemowuganjiazaiActivity::class.java)

    @Test
    fun testTabLayoutIsShow() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment_wuganjiazai_tablayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.fragment_ads_banner)).check(matches(isDisplayed()))
    }

    @Test
    fun testViewPager2IsShow() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment_wuganjiazai_viewpager2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.fragment_ads_banner)).check(matches(isDisplayed()))
    }
}