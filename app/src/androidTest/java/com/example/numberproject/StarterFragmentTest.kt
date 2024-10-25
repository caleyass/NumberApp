package com.example.numberproject

import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.numberproject.R
import com.example.numberproject.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StarterFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRandomFactButtonNavigation() {
        // Launch the activity
        ActivityScenario.launch(MainActivity::class.java)

        // Click the random fact button
        onView(withId(R.id.btn_get_random_fact)).perform(click())

        // Check if the navigation to NumberFactFragment is correct
        onView(withId(R.id.number_fact)).check(matches(isDisplayed()))
    }

//    @Test
//    fun testFactButtonWithEmptyInput() {
//        // Launch the activity
//        ActivityScenario.launch(MainActivity::class.java)
//
//        // Click the GET FACT button without entering a number
//        onView(withId(R.id.btn_get_fact)).perform(click())
//
//        // Verify that a Toast message is displayed
//        onView(withText("Input number!"))
//            .inRoot(ToastMatcher())
//            .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun testFactButtonWithEnteredNumber() {
//        // Launch the activity
//        ActivityScenario.launch(MainActivity::class.java)
//
//        // Enter a number
//        onView(withId(R.id.entered_number)).perform(typeText("42"), closeSoftKeyboard())
//
//        // Click the GET FACT button
//        onView(withId(R.id.btn_get_fact)).perform(click())
//
//        // Verify navigation occurs to the correct fragment
//        onView(withId(R.id.number_fact_fragment)).check(matches(isDisplayed()))
//    }
}
