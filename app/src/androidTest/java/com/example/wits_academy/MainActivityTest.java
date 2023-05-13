package com.example.wits_academy;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    private MainActivity login;

    @Before
    public void setUp() throws Exception {
        login = mActivityRule.getActivity();
    }

    @Test
    public void everythingIsDisplayed(){
        onView(withText("User number")).check(matches(isDisplayed()));
        onView(withText("Password")).check(matches((isDisplayed())));
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
        onView(withText("Forgot my password?")).check(matches(isDisplayed()));
    }
    @Test
    public void loginButtonClickable(){
        onView(ViewMatchers.withId(R.id.user_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.login_button)).perform(scrollTo()).perform(ViewActions.click());

        assertTrue(login.validate_input());
    }



    @After
    public void tearDown() throws Exception {
        login = null;
    }


}