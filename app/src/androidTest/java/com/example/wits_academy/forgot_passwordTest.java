package com.example.wits_academy;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
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
        Intents.init();
    }

//    @Test
//    public void everythingIsDisplayed(){
//        onView(withText("Email Address")).check(matches(isDisplayed()));
//        onView(withText("User number")).check(matches((isDisplayed())));
//        onView(withText("Confirm new password")).check(matches(isDisplayed()));
//        onView(withText("New password")).check(matches(isDisplayed()));
//        onView(withText("Reset Password")).check(matches(isDisplayed()));
//        onView(withText("Back to login")).check(matches(isDisplayed()));
//    }

    @Test
    public void ResetButtonClickable() throws Throwable {
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
                onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(click());
        assertTrue(forgot_password.validate_password());
    }

    @Test
    public void InvalidEmailEntered() throws Throwable {
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText("hlubgmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

                onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(click());

        assertFalse(forgot_password.validate_password());
    }

    @Test
    public void InvalidUserNumberEntered(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText("hlub@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());

        assertFalse(forgot_password.validate_password());
    }

    @Test
    public void InvalidPasswordEntered(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText("hlub@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("12367"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());

        assertFalse(forgot_password.validate_password());
    }

    @Test
    public void noMAILEntered(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("12367"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());

        assertFalse(forgot_password.validate_password());
    }

    @Test
    public void shortPasswordEntered(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText("hlub@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1233"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("1233"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());

        assertFalse(forgot_password.validate_password());
    }

    @Test
    public void cshortPasswordEntered(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(scrollTo()).perform(ViewActions.typeText("hlub@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(scrollTo()).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(scrollTo()).perform(ViewActions.typeText("1233456"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(scrollTo()).perform(ViewActions.typeText("1233"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());

        assertFalse(forgot_password.validate_password());
    }
//    @Test
//    public void goBackBackToLogin(){
//        Intent new_intent = new Intent();
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(MainActivity.class.getName())).respondWith(result);
//        onView(ViewMatchers.withId(R.id.login_button_f)).perform(scrollTo()).perform(ViewActions.click());
//        intended(hasComponent(forgot_password.class.getName()));
//    }

    @Test
    public void NothingIsNull(){
        onView(ViewMatchers.withId(R.id.f_enter_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_reset_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.f_enter_corfirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertNotNull(forgot_password.email.getText());
        assertNotNull(forgot_password.user_number.getText());
        assertNotNull(forgot_password.new_password.getText());
        assertNotNull(forgot_password.confirm_new_password.getText());
    }

//    @Test
//    public void AfterReset(){
//        Intent new_intent = new Intent();
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(MainActivity.class.getName())).respondWith(result);
//        onView(ViewMatchers.withId(R.id.reset_button)).perform(scrollTo()).perform(ViewActions.click());
////        intended(hasComponent(forgot_password.class.getName()));
//    }

    @Test
    public void goingBackToLoginFunction(){
        View view = forgot_password.findViewById(R.id.login_button_f);
        forgot_password.back_to_login(view);
    }

    @After
    public void tearDown() throws Exception {
        forgot_password = null;
        Intents.release();
    }
}