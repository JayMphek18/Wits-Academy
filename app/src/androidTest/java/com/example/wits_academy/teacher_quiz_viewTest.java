package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;

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

import java.util.ArrayList;

public class teacher_quiz_viewTest {

    @Rule
    public ActivityTestRule<teacher_quiz_view> mActivityRule = new ActivityTestRule(teacher_quiz_view.class);
    private teacher_quiz_view teacher_quiz;
    private String courseName;
    private String userNumber;
    private DrawerLayout drawerLayout;
    LinearLayout course_list;
    private NavigationView navigationView;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        teacher_quiz = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = teacher_quiz.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav_t);
        menu = navigationView.getMenu();
        userNumber = teacher_quiz.userNumber = "1234567";
        course_list = teacher_quiz.findViewById(R.id.quiz_view);
        courseName = teacher_quiz.courseName = "Advance Analysis";
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
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem3(){
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(2);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem2(){
        MenuItem menuItem = menu.getItem(2);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem1(){
        MenuItem menuItem = menu.getItem(1);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem0(){
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(0);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem1(){
        MenuItem menuItem = menu.getItem(5).getSubMenu().getItem(1);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem0(){
        MenuItem menuItem = menu.getItem(0);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem4(){
        MenuItem menuItem = menu.getItem(4);
        assertTrue(teacher_quiz.onNavigationItemSelected(menuItem));
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
                teacher_quiz.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void backPressClosed() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                teacher_quiz.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void addViewGrades() throws Throwable {
        ArrayList<String> quizName = new ArrayList<>();
        ArrayList<String> marks = new ArrayList<>();
        quizName.add("Quiz 1");
        marks.add("100");
        quizName.add("Quiz 2");
        marks.add("100");
        quizName.add("Quiz 3");
        marks.add("100");
        quizName.add("Quiz 4");
        marks.add("100");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.quizzes(teacher_quiz.getApplicationContext(), course_list, quizName,marks,userNumber,courseName);
                View view  = course_list.getChildAt(1);
                View image = view.findViewById(R.id.grades);
                image.performClick();
            }
        });
        assertEquals(course_list.getChildCount(), 5);
    }
    @Test
    public void deleteQuiz() throws Throwable {
        ArrayList<String> quizName = new ArrayList<>();
        ArrayList<String> marks = new ArrayList<>();
        quizName.add("Quiz 1");
        marks.add("100");
        quizName.add("Quiz 2");
        marks.add("100");
        quizName.add("Quiz 3");
        marks.add("100");
        quizName.add("Quiz 4");
        marks.add("100");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.quizzes(teacher_quiz.getApplicationContext(), course_list, quizName,marks,userNumber,courseName);
                View view  = course_list.getChildAt(1);
                View image = view.findViewById(R.id.imageView10);
                image.performClick();
            }
        });
        assertEquals(course_list.getChildCount(), 5);
    }
    @After
    public void tearDown() throws Exception {
    }
}