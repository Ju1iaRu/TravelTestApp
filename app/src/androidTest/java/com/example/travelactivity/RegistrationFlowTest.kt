package com.app.travelactivity

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.app.travelactivity.R
import com.app.travelactivity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationFlowTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun successfulRegistration_opensLoginScreen() {
        // Use a unique username so the test works even if it runs multiple times
        val username = "user_${System.currentTimeMillis()}"

        // Open the Create Account screen
        onView(withId(R.id.textViewRegister))
            .perform(click())

        // Fill in the registration form
        onView(withId(R.id.editTextUsername))
            .perform(typeText(username), closeSoftKeyboard())

        onView(withId(R.id.editTextPassword))
            .perform(typeText("pass1234"), closeSoftKeyboard())

        onView(withId(R.id.editTextConfirmPassword))
            .perform(typeText("pass1234"), closeSoftKeyboard())

        // Click the Register button
        onView(withId(R.id.buttonRegister))
            .perform(click())

        // After successful registration, the RegistrationActivity finishes
        // and the Login screen should be visible again.
        onView(withId(R.id.buttonLogin))
            .check(matches(isDisplayed()))
    }
}

