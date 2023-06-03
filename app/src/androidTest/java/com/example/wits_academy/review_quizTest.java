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
import android.widget.LinearLayout;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class review_quizTest {
    @Rule
    public ActivityTestRule<review_quiz> mActivityRule = new ActivityTestRule(review_quiz.class);
    private review_quiz review;
    private String courseName;
    private String quizName;
    private String userNumber ;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;
    LinearLayout quiz_review;
    @Before
    public void setUp() throws Exception {
        review = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = review.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav_s);
        quiz_review = review.findViewById(R.id.contents);
        menu = navigationView.getMenu();
        userNumber = review.userNumber = "1234567";
        courseName = review.courseName = "Advance Analysis";
        quizName = review.quizName = "Quiz 1";
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
    public void clickedMenuItem3(){
        MenuItem menuItem = menu.getItem(3);
        assertTrue(review.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem2(){
        MenuItem menuItem = menu.getItem(2);
        assertTrue(review.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem1(){
        MenuItem menuItem = menu.getItem(1);
        assertTrue(review.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem0(){
        MenuItem menuItem = menu.getItem(4).getSubMenu().getItem(0);
        assertTrue(review.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedSubMenuItem1(){
        MenuItem menuItem = menu.getItem(4).getSubMenu().getItem(1);
        assertTrue(review.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem0(){
        MenuItem menuItem = menu.getItem(0);
        assertTrue(review.onNavigationItemSelected(menuItem));
    }
    
    @Test
    public void menuItemOneTest() throws Throwable {
        MenuItem menuItem = menu.getItem(0);
        assertEquals(menuItem.getItemId(), R.id.announcements);
        assertEquals(menuItem.getTitle(), "Announcements");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//                onView(ViewMatchers.withId(R.id.announcements)).perform(scrollTo()).perform(ViewActions.click());

//
    }
    @Test
    public void menuItemTwoTest() throws Throwable {
        MenuItem menuItem = menu.getItem(1);
        assertEquals(menuItem.getItemId(), R.id.back);
        assertEquals(menuItem.getTitle(), "Course Dashboard");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//                onView(ViewMatchers.withId(R.id.back)).perform(scrollTo()).perform(ViewActions.click());

//
    }
    @Test
    public void menuItemThreeTest() throws Throwable {
        MenuItem menuItem = menu.getItem(2);
        assertEquals(menuItem.getItemId(), R.id.logout);
        assertEquals(menuItem.getTitle(), "Logout");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//                onView(ViewMatchers.withId(R.id.logout)).perform(scrollTo()).perform(ViewActions.click());
//
    }
    @Test
    public void menuItemFourTest() throws Throwable {
        MenuItem menuItem = menu.getItem(3);
        assertEquals(menuItem.getItemId(), R.id.ViewUsers);
        assertEquals(menuItem.getTitle(), "View participants");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//                onView(ViewMatchers.withId(R.id.ViewUsers)).perform(scrollTo()).perform(ViewActions.click());

    }

    @Test
    public void subMenuItemOneTest() throws Throwable {
        MenuItem menuItem = menu.getItem(4).getSubMenu().getItem(0);
        assertEquals(menuItem.getItemId(), R.id.courseHomeActivity);
        assertEquals(menuItem.getTitle(), "Course Homepage");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
//                onView(ViewMatchers.withId(R.id.courseHomeActivity)).perform(scrollTo()).perform(ViewActions.click());
//
    }
    @Test
    public void subMenuItemTwoTest() throws Throwable {
        MenuItem menuItem = menu.getItem(4).getSubMenu().getItem(1);
        assertEquals(menuItem.getItemId(), R.id.quiz);
        assertEquals(menuItem.getTitle(), "Quizzes");
//        Intent new_intent = new Intent(mActivityRule.getActivity(), profile.class);
//        new_intent.putExtra("userNumber", userNumber);
//        new_intent.putExtra("has_image", "");
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(profile.class.getPackage().getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));

//                onView(ViewMatchers.withId(R.id.quiz)).perform(scrollTo()).perform(ViewActions.click());
//
    }

    @Test
    public void backPressOpen() throws Throwable {
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                review.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void backPressClosed() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                review.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void addReview() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.review(review.getApplicationContext(), quiz_review, "1", "What was the question", "I don't know", "question 1", "5");
                ViewsClass.review(review.getApplicationContext(), quiz_review, "1", "What was the question", "I don't know", "question 1", "5");
                ViewsClass.review(review.getApplicationContext(), quiz_review, "1", "What was the question", "I don't know", "question 1", "5");
            }
        });
        assertEquals(quiz_review.getChildCount(), 4);
    }

    @After
    public void tearDown() throws Exception {
        review = null;
    }
}