package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.intent.Intents.getIntents;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
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

public class student_quiz_viewTest {
    @Rule
    public ActivityTestRule<student_quiz_view> mActivityRule = new ActivityTestRule(student_quiz_view.class);
    private student_quiz_view student_quiz;
    private String courseName;
    private String userNumber ;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;
    LinearLayout available_quizzes;
    LinearLayout past_quizzes;

    @Before
    public void setUp() throws Exception {
        student_quiz = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = student_quiz.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav_s);
        available_quizzes = student_quiz.findViewById(R.id.available);
        past_quizzes = student_quiz.findViewById(R.id.past);
        menu = navigationView.getMenu();
        userNumber = student_quiz.userNumber = "1234567";
        courseName = student_quiz.courseName = "Advance Analysis";
        Intents.init();
    }

    @Test
    public void testMenuItems(){
        for (int i = 0 ; i < 5; i++){
            MenuItem menuItem = menu.getItem(i);
            if (i == 4){
                SubMenu subMenu = menuItem.getSubMenu();
                assertNotNull(subMenu.getItem(0));
                assertNotNull(subMenu.getItem(1));
            }
            assertNotNull(menuItem);
        }
    }
    @Test
    public void menuItemOneTest(){
        MenuItem menuItem = menu.getItem(0);
        assertEquals(menuItem.getItemId(), R.id.announcements);
        assertEquals(menuItem.getTitle(), "Announcements");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//        onView(ViewMatchers.withId(R.id.announcements)).perform(scrollTo()).perform(ViewActions.click());
    }
    @Test
    public void menuItemTwoTest(){
        MenuItem menuItem = menu.getItem(1);
        assertEquals(menuItem.getItemId(), R.id.back);
        assertEquals(menuItem.getTitle(), "Course Dashboard");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//        onView(ViewMatchers.withId(R.id.back)).perform(scrollTo()).perform(ViewActions.click());
    }
    @Test
    public void menuItemThreeTest(){
        MenuItem menuItem = menu.getItem(2);
        assertEquals(menuItem.getItemId(), R.id.logout);
        assertEquals(menuItem.getTitle(), "Logout");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//        onView(ViewMatchers.withId(R.id.logout)).perform(scrollTo()).perform(ViewActions.click());
    }
    @Test
    public void menuItemFourTest(){
        MenuItem menuItem = menu.getItem(3);
        assertEquals(menuItem.getItemId(), R.id.ViewUsers);
        assertEquals(menuItem.getTitle(), "View participants");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//        onView(ViewMatchers.withId(R.id.ViewUsers)).perform(scrollTo()).perform(ViewActions.click());
    }

    @Test
    public void subMenuItemOneTest(){
        MenuItem menuItem = menu.getItem(4).getSubMenu().getItem(0);
        assertEquals(menuItem.getItemId(), R.id.courseHomeActivity);
        assertEquals(menuItem.getTitle(), "Course Homepage");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//        onView(ViewMatchers.withId(R.id.courseHomeActivity)).perform(scrollTo()).perform(ViewActions.click());
    }
    @Test
    public void subMenuItemTwoTest(){
        MenuItem menuItem = menu.getItem(4).getSubMenu().getItem(1);
        assertEquals(menuItem.getItemId(), R.id.quiz);
        assertEquals(menuItem.getTitle(), "Quizzes");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//        onView(ViewMatchers.withId(R.id.quiz)).perform(scrollTo()).perform(ViewActions.click());
    }

    @Test
    public void addAvailableQuiz() throws Throwable {
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
                ViewsClass.available_quizzes(student_quiz.getApplicationContext(), available_quizzes,
                        quizName, marks,userNumber,courseName);
                for (int i = 1; i < available_quizzes.getChildCount(); i++){
                    View view = available_quizzes.getChildAt(i).findViewById(R.id.review_quiz);
                    assertTrue(view.hasOnClickListeners());
//                    Button back = student_quiz.findViewById(R.id.button);
//                    back.performClick();
                }
            }
        });
        assertEquals(student_quiz.available_quizzes.getChildCount(), 5);
    }

    @Test
    public void addPastQuiz() throws Throwable {
        ArrayList<String> quizName = new ArrayList<>();
        ArrayList<String> marks = new ArrayList<>();
        quizName.add("Quiz 4");
        marks.add("24");
        quizName.add("Quiz 5");
        marks.add("66");
        quizName.add("Quiz 6");
        marks.add("59");
        quizName.add("Quiz 7");
        marks.add("88");
        quizName.add("Quiz 8");
        marks.add("78");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.past_quizzes(student_quiz.getApplicationContext(), past_quizzes,
                        quizName, marks,userNumber,courseName);
                for (int i = 1; i < past_quizzes.getChildCount(); i++){
                    View view = past_quizzes.getChildAt(i).findViewById(R.id.review_quiz);
                    view.performClick();

                }
            }
        });
        assertEquals(student_quiz.past_quizzes.getChildCount(), 6);
    }

    @After
    public void tearDown() throws Exception {
        student_quiz = null;
        Intents.release();
    }
}