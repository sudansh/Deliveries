package com.sudansh.deliveries.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.sudansh.deliveries.R
import com.sudansh.deliveries.createDelivery
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MapsActivityTest {
    @get:Rule
    var mActivityRule = IntentsTestRule<MapsActivity>(MapsActivity::class.java, false, false)

    @Test
    fun launchWithValidIntent() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(context, MapsActivity::class.java)
        intent.putExtra(MapsActivity.KEY_DELIVERY, createDelivery(1, "foo", 123.0, 456.0, "bar"))
        mActivityRule.launchActivity(intent)

        onView(ViewMatchers.withId(R.id.description)).check(ViewAssertions.matches(withText("foo")))

    }
}