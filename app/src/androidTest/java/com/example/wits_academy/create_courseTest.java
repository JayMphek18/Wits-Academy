package com.example.wits_academy;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import android.view.Menu;
import android.view.MenuItem;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

public class create_courseTest {

    @Rule
    public ActivityTestRule<create_course> mActivityRule = new ActivityTestRule(create_course.class);
    private create_course create;
    private String userNumber ;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        create = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = create.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav);
        menu = navigationView.getMenu();
        userNumber = create.userNumber = "1234567";
        Intents.init();
    }

    @Test
    public void fillinTest(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText("MATH"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText("MATH1005"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText("School of Mathematics"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText("Faculty of Science"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        onView(ViewMatchers.withId(R.id.create_button)).perform(scrollTo()).perform(click());
        assertTrue(create.filled_in());
    }
    @Test
    public void noCourseName(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText("MATH1005"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText("School of Mathematics"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText("Faculty of Science"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertFalse(create.filled_in());
    }

    @Test
    public void noCourseCode(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText("MATH"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText("School of Mathematics"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText("Faculty of Science"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertFalse(create.filled_in());
    }

    @Test
    public void noSchoolName(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText("MATH"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText("MATH1005"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText("Faculty of Science"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertFalse(create.filled_in());
    }

    @Test
    public void noFacultyName(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText("MATH"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText("MATH1005"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText("School of Mathematics"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertFalse(create.filled_in());
    }

    @Test
    public void PasswordDoesNotMatch(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText("MATH"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText("MATH1005"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText("School of Mathematics"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText("Faculty of Science"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234444"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234567"));
        closeSoftKeyboard();

        assertFalse(create.filled_in());
    }
    @Test
    public void passwordShort(){
        onView(ViewMatchers.withId(R.id.course_name)).perform(ViewActions.typeText("MATH"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_code)).perform(ViewActions.typeText("MATH1005"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_school)).perform(ViewActions.typeText("School of Mathematics"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_faculty)).perform(ViewActions.typeText("Faculty of Science"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.course_password)).perform(ViewActions.typeText("1234"));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.confirm_c_password)).perform(ViewActions.typeText("1234"));
        closeSoftKeyboard();

        assertFalse(create.filled_in());
    }

//     @Test
//     public void spinnerDisplay(){
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(0).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("1 - First Year"))));
//         assertEquals(create.year.toString(), "1");
//         closeSoftKeyboard();
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(1).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("2 - Second Year"))));
//         assertEquals(create.year.toString(), "2");
//         closeSoftKeyboard();
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(2).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("3 - Third Year"))));
//         assertEquals(create.year.toString(), "3");
//         closeSoftKeyboard();
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(3).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("4 - Forth Year"))));
//         assertEquals(create.year.toString(), "4");
//         closeSoftKeyboard();
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(4).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("5 - Fifth Year"))));
//         assertEquals(create.year.toString(), "5");
//         closeSoftKeyboard();
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(5).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("6 - Sixth Year"))));
//         assertEquals(create.year.toString(), "6");
//         closeSoftKeyboard();
//         onView(withId(R.id.year_of_study)).perform(click());
//         onData(anything()).atPosition(6).perform(click());
// //        onView(withId(R.id.year_of_study)).perform(scrollTo()).check(matches(withSpinnerText(containsString("7 - Seventh Year"))));
//         assertEquals(create.year.toString(), "7");
//         closeSoftKeyboard();
//     }

    @Test
    public void testMenuItems(){
        for (int i = 0 ; i < 4; i++){
            MenuItem menuItem = menu.getItem(i);
            assertNotNull(menuItem);
        }
    }

    @Test
    public void menuItemOneTest(){
        MenuItem menuItem = menu.getItem(0);
        assertEquals(menuItem.getItemId(), R.id.profile);
        assertEquals(menuItem.getTitle(), "View/Edit Profile");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        onView(ViewMatchers.withId(R.id.profile)).perform(scrollTo()).perform(ViewActions.click());
    }

    @Test
    public void menuItemTwoTest(){
        MenuItem menuItem = menu.getItem(1);
        assertEquals(menuItem.getItemId(), R.id.create);
        assertEquals(menuItem.getTitle(), "Create a new Course");
//        Intent new_intent = new Intent();
//        new_intent.putExtra("userNumber", userNumber);
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(search_courses.class.getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        onView(ViewMatchers.withId(R.id.create)).perform(scrollTo()).perform(ViewActions.click());
    }

    @Test
    public void menuItemThreeTest(){
        MenuItem menuItem = menu.getItem(2);
        assertEquals(menuItem.getItemId(), R.id.menu_page);
        assertEquals(menuItem.getTitle(), "Back to Menu");
//        Intent new_intent = new Intent();
//        new_intent.putExtra("userNumber", userNumber);
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(search_courses.class.getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        onView(ViewMatchers.withId(R.id.menu_page)).perform(scrollTo()).perform(ViewActions.click());
    }

    @Test
    public void menuItemFourTest(){
        MenuItem menuItem = menu.getItem(3);
        assertEquals(menuItem.getItemId(), R.id.logout);
        assertEquals(menuItem.getTitle(), "Logout");
//        Intent new_intent = new Intent();
//        new_intent.putExtra("userNumber", userNumber);
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(search_courses.class.getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        onView(ViewMatchers.withId(R.id.logout)).perform(scrollTo()).perform(ViewActions.click());
    }

    @After
    public void tearDown() throws Exception {
        create = null;
        Intents.release();
    }
}
