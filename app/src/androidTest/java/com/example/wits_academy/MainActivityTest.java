package com.example.wits_academy;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
//    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);
    private MainActivity login;

    @Before
    public void setUp() throws Exception {
        login = mActivityRule.getActivity();
        Intents.init();
    }

//     @Test
//     public void everythingIsDisplayed(){
//         onView(withId(R.id.imageView)).check(matches(isDisplayed()));
//         onView(withText("User number")).check(matches(isDisplayed()));
//         onView(withText("Password")).check(matches((isDisplayed())));
//         onView(withText("Login")).check(matches(isDisplayed()));
//         onView(withText("Forgot my password?")).check(matches(isDisplayed()));
//         onView(withText("Register")).check(matches(isDisplayed()));
//     }
    @Test
    public void loginButtonClickable(){
        onView(ViewMatchers.withId(R.id.user_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        Intent login_intent = new Intent();
        login_intent.putExtra("user_number" , "1234567");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, login_intent);
        intending(toPackage(student_course_view.class.getName())).respondWith(result);
        onView(ViewMatchers.withId(R.id.login_button)).perform(scrollTo()).perform(ViewActions.click());

        assertTrue(login.validate_input());
    }

    @Test
    public void invalidInputUserNumber(){
        onView(ViewMatchers.withId(R.id.user_number)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertFalse(login.validate_input());
    }
    @Test
    public void invalidInputUserPassword(){
        onView(ViewMatchers.withId(R.id.user_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_password)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();

        assertFalse(login.validate_input());
    }
    @Test
    public void goToForgotPassword(){
        Intent new_intent = new Intent();
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
        intending(toPackage(forgot_password.class.getName())).respondWith(result);
        onView(ViewMatchers.withId(R.id.forgot_password)).perform(scrollTo()).perform(ViewActions.click());
//        intended(hasComponent(forgot_password.class.getName()));
    }
    @Test
    public void functionToRegister(){
        View view = login.findViewById(R.id.registration_button);
        login.registration_page(view);
    }

    @Test
    public void functionToForgotPassword(){
        View view = login.findViewById(R.id.forgot_password);
        login.registration_page(view);
    }

    @Test
    public void goToRegister(){
        Intent new_intent = new Intent();
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
        intending(toPackage(register_page.class.getName())).respondWith(result);
        onView(ViewMatchers.withId(R.id.registration_button)).perform(scrollTo()).perform(ViewActions.click());
//        intended(hasComponent(forgot_password.class.getName()));
    }
    @After
    public void tearDown() throws Exception {
        login = null;
        Intents.release();
    }


}
