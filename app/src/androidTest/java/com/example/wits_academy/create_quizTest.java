package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.LinearLayout;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class create_quizTest {

    @Rule
    public ActivityTestRule<create_quiz> mActivityRule = new ActivityTestRule(create_quiz.class);
    private create_quiz quiz;
    private String courseName;
    private String userNumber;
    private DrawerLayout drawerLayout;
    LinearLayout course_list;
    private NavigationView navigationView;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        quiz = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = quiz.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav_t);
        menu = navigationView.getMenu();
        userNumber = quiz.userNumber = "1234567";
        course_list = quiz.findViewById(R.id.quiz_questions);
        courseName = quiz.courseName = "Advance Analysis";
    }

    @Test
    public void testMenuItems(){
        for (int i = 0 ; i < 6; i++){
            MenuItem menuItem = menu.getItem(i);
            if (i == 5){
                SubMenu subMenu = menuItem.getSubMenu();
                assertNotNull(subMenu.getItem(0));
                assertNotNull(subMenu.getItem(1));
                assertNotNull(subMenu.getItem(2));
            }
            assertNotNull(menuItem);
        }
    }
    @Test
    public void clickedMenuItem3(){
        MenuItem menuItem = menu.getItem(3);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem3(){
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(2);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem2(){
        MenuItem menuItem = menu.getItem(2);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem1(){
        MenuItem menuItem = menu.getItem(1);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem0(){
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(0);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem1(){
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(1);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem0(){
        MenuItem menuItem = menu.getItem(0);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem4(){
        MenuItem menuItem = menu.getItem(4);
        assertTrue(quiz.onNavigationItemSelected(menuItem));
    }

    @Test
    public void menuItemOneTest() throws Throwable {
        MenuItem menuItem = menu.getItem(0);
        assertEquals(menuItem.getItemId(), R.id.Announcement);
        assertEquals(menuItem.getTitle(), "Announcements");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.Announcement)).perform(scrollTo()).perform(ViewActions.click());

//
    }
    @Test
    public void menuItemTwoTest() throws Throwable {
        MenuItem menuItem = menu.getItem(1);
        assertEquals(menuItem.getItemId(), R.id.other_quizzes);
        assertEquals(menuItem.getTitle(), "Past Quizzes");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.other_quizzes)).perform(scrollTo()).perform(ViewActions.click());

//
    }
    @Test
    public void menuItemThreeTest() throws Throwable {
        MenuItem menuItem = menu.getItem(2);
        assertEquals(menuItem.getItemId(), R.id.back);
        assertEquals(menuItem.getTitle(), "Course Dashboard");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.back)).perform(scrollTo()).perform(ViewActions.click());

//
    }
    @Test
    public void menuItemFourTest() throws Throwable {
        MenuItem menuItem = menu.getItem(3);
        assertEquals(menuItem.getItemId(), R.id.logout);
        assertEquals(menuItem.getTitle(), "Logout");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.logout)).perform(scrollTo()).perform(ViewActions.click());

//
    }

    @Test
    public void menuItemFiveTest() throws Throwable {
        MenuItem menuItem = menu.getItem(4);
        assertEquals(menuItem.getItemId(), R.id.ViewUsers);
        assertEquals(menuItem.getTitle(), "View participants ");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.ViewUsers)).perform(scrollTo()).perform(ViewActions.click());

//
    }



    @Test
    public void subMenuItemOneTest() throws Throwable {
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(0);
        assertEquals(menuItem.getItemId(), R.id.course_slides);
        assertEquals(menuItem.getTitle(), "Upload Documents");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.course_slides)).perform(scrollTo()).perform(ViewActions.click());

//
    }

    @Test
    public void subMenuItemTwoTest() throws Throwable {
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(1);
        assertEquals(menuItem.getItemId(), R.id.videos);
        assertEquals(menuItem.getTitle(), "Upload Videos");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.videos)).perform(scrollTo()).perform(ViewActions.click());

//
    }
    @Test
    public void subMenuItemThreeTest() throws Throwable {
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(2);
        assertEquals(menuItem.getItemId(), R.id.quiz);
        assertEquals(menuItem.getTitle(), "Create Quiz");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//        onView(ViewMatchers.withId(R.id.quiz)).perform(scrollTo()).perform(ViewActions.click());

//
    }

    @Test
    public void backPressOpen() throws Throwable {
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                quiz.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void backPressClosed() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                quiz.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @After
    public void tearDown() throws Exception {
    }
}