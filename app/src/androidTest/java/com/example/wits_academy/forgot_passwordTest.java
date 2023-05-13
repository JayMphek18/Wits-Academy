package com.example.wits_academy;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class forgot_passwordTest {

    @Rule
    public ActivityTestRule<forgot_password> mActivityRule = new ActivityTestRule(forgot_password.class);
    private forgot_password forgot_password;

    @Before
    public void setUp() throws Exception {
        forgot_password = mActivityRule.getActivity();
    }

    @Test
    public void everythingIsDisplayed(){
        onView(withText("Email Address")).check(matches(isDisplayed()));
        onView(withText("User number")).check(matches((isDisplayed())));
        onView(withText("Confirm new password")).check(matches(isDisplayed()));
        onView(withText("New password")).check(matches(isDisplayed()));
        onView(withText("Reset Password")).check(matches(isDisplayed()));
        onView(withText("Back to login")).check(matches(isDisplayed()));
    }

    @Test
    public void ResetButtonClickable(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());

        assertTrue(forgot_password.validate_password());


    }



    @After
    public void tearDown() throws Exception {
        forgot_password = null;
    }
}