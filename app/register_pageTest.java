package com.example.wits_academy;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.getIntents;
import static androidx.test.espresso.intent.Intents.intending;
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
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class register_pageTest {
    @Rule
    public ActivityTestRule<register_page> mActivityRule = new ActivityTestRule(register_page.class);
//    public IntentsTestRule<register_page> intentsTestRule = new IntentsTestRule<>(register_page.class);

    private register_page register_page;

    @Before
    public void setUp() throws Exception {
        register_page = mActivityRule.getActivity();
        Intents.init();
    }

    @Test
    public void backToLoginFunction(){
        View view = register_page.findViewById(R.id.login_button);
        register_page.login(view);
        Intents.init();
    }

//    @Test
//    public void allTextVisible(){
//        onView(withText("Email Address")).check(matches(isDisplayed()));
//        onView(withText("Student number")).check(matches((isDisplayed())));
//        onView(withText("First Name")).check(matches(isDisplayed()));
//        onView(withText("Last Name")).check(matches(isDisplayed()));
//        onView(withText("Password")).check(matches(isDisplayed()));
//        onView(withText("Confirm Password")).check(matches(isDisplayed()));
//        onView(withText("Register")).check(matches(isDisplayed()));
//        onView(withText("Back to login")).check(matches(isDisplayed()));
//    }

    @Test
    public void allfilledIn(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertTrue(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }

    @Test
    public void spinnerDisplay(){
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Teacher"))));
        onView(withText("Employee number")).check(matches(isDisplayed()));
        closeSoftKeyboard();
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Student"))));
        onView(withText("Student number")).check(matches(isDisplayed()));
        closeSoftKeyboard();
    }

    @Test
    public void invalidUserName(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }

    @Test
    public void invalidUserSurname(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }

    @Test
    public void invalidUserNumber(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }

    @Test
    public void invalidUserEmail(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertFalse(register_page.isEmailValid());
    }

    @Test
    public void invalidUserPassword(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234566"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }
    @Test
    public void ShortUserPassword(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }


    public void ShortUserConfirmPassword(){
        onView(ViewMatchers.withId(R.id.user_email)).perform(ViewActions.typeText("hlubi@gmail.com"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.user_r_number)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.first_name)).perform(ViewActions.typeText("Zenzele"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.last_name)).perform(ViewActions.typeText("Hlubi"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.create_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("1234"));
        closeSoftKeyboard();
//        onView(ViewMatchers.withId(R.id.register_button)).perform(scrollTo()).perform(click());

        assertFalse(register_page.filled_in());
        assertTrue(register_page.isEmailValid());
    }
    @Test
    public void goBackBackToLogin(){
        View view = register_page.findViewById(R.id.login_button);
        register_page.login(view);
        Intent login = new Intent(register_page.getApplicationContext(), MainActivity.class);
        String z = getIntents().get(0).toString();
        String intent = "";
        for(int i = 0; i < z.length(); i++){
            if (z.charAt(i)== '}'){
                intent = intent + z.charAt(i);
                break;
            }
            else{
                intent = intent + z.charAt(i);
            }
        }
        assertEquals(login.toString(), intent);
    }

    @After
    public void tearDown() throws Exception {
        register_page = null;
        Intents.release();
    }
}