package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class search_coursesTest {

    @Rule
    public ActivityTestRule<search_courses> mActivityRule = new ActivityTestRule(search_courses.class);
    private search_courses search_;
    private String userNumber ;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        search_ = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = search_.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav);
        menu = navigationView.getMenu();
        userNumber = search_.userNumber = "1234567";
        Intents.init();
    }

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
        assertEquals(menuItem.getItemId(), R.id.search);
        assertEquals(menuItem.getTitle(), "Available Courses");
//        Intent new_intent = new Intent();
//        new_intent.putExtra("userNumber", userNumber);
//        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, new_intent);
//        intending(toPackage(search_courses.class.getName())).respondWith(result);
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        onView(ViewMatchers.withId(R.id.search)).perform(scrollTo()).perform(ViewActions.click());
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

    @Test
    public void testSearchMenuIsClickable(){
        Menu new_menu = new MenuBuilder(search_.getBaseContext());
//        MenuInflater inflater = search_.getMenuInflater();
//        inflater.inflate(R.menu.search_bar, new_menu);
//        MenuItem searchViewItem = new_menu.findItem(R.id.search_menu_bar);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        onView(ViewMatchers.withId(R.id.search_menu_bar)).perform(ViewActions.click());
        assertTrue(search_.onCreateOptionsMenu(new_menu));
    }

    @Test
    public void testSearchMenuIsClickableAndAbleToEnterString(){
        Menu new_menu = new MenuBuilder(search_.getBaseContext());
        MenuInflater inflater = search_.getMenuInflater();
        inflater.inflate(R.menu.search_bar, new_menu);
        MenuItem searchViewItem = new_menu.findItem(R.id.search_menu_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setQuery("ballls", false);
    }

    @Test
    public void addingTwoCoursesCoursesInPage() throws Throwable {
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();
        courseCode.add("MATH1001");
        courseName.add("Basic Analysis");
        teacherName.add("Sethembile");
        courseCode.add("MATH2003");
        courseName.add("Abstract Maths");
        teacherName.add("Martin");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                search_.course_list.removeAllViews();
                ViewsClass.viewlayout(2, 0, search_.getBaseContext() , courseName, userNumber, courseCode, teacherName, search_.course_list);
            }
        });
        assertEquals(search_.course_list.getChildCount(), 1);
    }
    @Test
    public void addingOneCoursesCoursesInPage() throws Throwable {
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();
        courseCode.add("MATH1001");
        courseName.add("Basic Analysis");
        teacherName.add("Sethembile");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                
//                search_.course_list.removeAllViews();
                ViewsClass.viewlayout(1, 0, search_.getBaseContext() , courseName, userNumber, courseCode, teacherName, search_.course_list);
            }
        });
        assertEquals(search_.course_list.getChildCount(), 1);
    }

    @Test
    public void AddOddNumberOfCoursesStartingFromJSONArrayBeforeSearching() throws Throwable {
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray search_CourseArray = new JSONArray();
        JSONObject search_Course1 = new JSONObject();
        search_Course1.put("teacher_id", "Sethembile");
        search_Course1.put("course_code", "MATH1002");
        search_Course1.put("course_name", "Basic Analysis");
        search_CourseArray.put(search_Course1);
        JSONObject search_Course2 = new JSONObject();
        search_Course2.put("teacher_id", "Martin");
        search_Course2.put("course_code", "STAT1002");
        search_Course2.put("course_name", "Intro to Stats");
        search_CourseArray.put(search_Course2);
        JSONObject search_Course3 = new JSONObject();
        search_Course3.put("teacher_id", "Zenzele");
        search_Course3.put("course_code", "COMS1004");
        search_Course3.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course3);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                
//                search_.course_list.removeAllViews();
                ViewsClass.get_information(search_.getBaseContext(),"",search_.course_list, search_CourseArray,
                        teacherName, courseCode, courseName, userNumber);
                assertEquals(search_.course_list.getChildCount(), 2);
                for (int i = 1; i <= search_.course_list.getChildCount() - 3; i++){
                    View view = search_.course_list.getChildAt(search_.course_list.getChildCount() - i).findViewById(R.id.enroll_botton);
                    view.performClick();

                }
                View view2 = search_.course_list.getChildAt(search_.course_list.getChildCount() - 1).findViewById(R.id.enroll_botton2);
                view2.performClick();
            }
        });


    }
    @Test
    public void AddEvenNumberOfCoursesStartingFromJSONArrayBeforeSearching() throws Throwable {
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray search_CourseArray = new JSONArray();
        JSONObject search_Course1 = new JSONObject();
        search_Course1.put("teacher_id", "Sethembile");
        search_Course1.put("course_code", "MATH1002");
        search_Course1.put("course_name", "Basic Analysis");
        search_CourseArray.put(search_Course1);
        JSONObject search_Course2 = new JSONObject();
        search_Course2.put("teacher_id", "Martin");
        search_Course2.put("course_code", "STAT1002");
        search_Course2.put("course_name", "Intro to Stats");
        search_CourseArray.put(search_Course2);
        JSONObject search_Course3 = new JSONObject();
        search_Course3.put("teacher_id", "Zenzele");
        search_Course3.put("course_code", "COMS1004");
        search_Course3.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course3);
        JSONObject search_Course4 = new JSONObject();
        search_Course4.put("teacher_id", "Zenzele");
        search_Course4.put("course_code", "COMS1004");
        search_Course4.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course4);
        JSONObject search_Course5 = new JSONObject();
        search_Course5.put("teacher_id", "Zenzele");
        search_Course5.put("course_code", "COMS1004");
        search_Course5.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course5);
        JSONObject search_Course6 = new JSONObject();
        search_Course6.put("teacher_id", "Zenzele");
        search_Course6.put("course_code", "COMS1004");
        search_Course6.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course6);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                
//                search_.course_list.removeAllViews();
                ViewsClass.get_information(search_.getBaseContext(),"",search_.course_list, search_CourseArray,
                        teacherName, courseCode, courseName, userNumber);
                assertEquals(search_.course_list.getChildCount(), 3);
                for (int i = 1; i <= search_.course_list.getChildCount() - 3; i++){
                    View view = search_.course_list.getChildAt(search_.course_list.getChildCount() - i).findViewById(R.id.enroll_botton);
                    view.performClick();
                    View view2 = search_.course_list.getChildAt(search_.course_list.getChildCount() - i).findViewById(R.id.enroll_botton2);
                    view2.performClick();
                }
            }
        });

    }
    @Test
    public void AddOddNumberOfCoursesStartingFromJSONArrayUsingSearching() throws Throwable {
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray search_CourseArray = new JSONArray();
        JSONObject search_Course1 = new JSONObject();
        search_Course1.put("teacher_id", "Sethembile");
        search_Course1.put("course_code", "MATH1002");
        search_Course1.put("course_name", "Basic Analysis");
        search_CourseArray.put(search_Course1);
        JSONObject search_Course2 = new JSONObject();
        search_Course2.put("teacher_id", "Martin");
        search_Course2.put("course_code", "STAT1002");
        search_Course2.put("course_name", "Intro to Stats");
        search_CourseArray.put(search_Course2);
        JSONObject search_Course3 = new JSONObject();
        search_Course3.put("teacher_id", "Zenzele");
        search_Course3.put("course_code", "COMS1004");
        search_Course3.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course3);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                search_.course_list.removeAllViews();
                ViewsClass.get_information(search_.getBaseContext(),"Basic",search_.course_list, search_CourseArray,
                        teacherName, courseCode, courseName, userNumber);
            }
        });

        assertEquals(search_.course_list.getChildCount(), 1);
    }

    @Test
    public void AddEvenNumberOfCoursesStartingFromJSONArrayUsingSearching() throws Throwable {
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray search_CourseArray = new JSONArray();
        JSONObject search_Course1 = new JSONObject();
        search_Course1.put("teacher_id", "Sethembile");
        search_Course1.put("course_code", "MATH1002");
        search_Course1.put("course_name", "Basic Analysis");
        search_CourseArray.put(search_Course1);
        JSONObject search_Course2 = new JSONObject();
        search_Course2.put("teacher_id", "Martin");
        search_Course2.put("course_code", "STAT1002");
        search_Course2.put("course_name", "Intro to Stats");
        search_CourseArray.put(search_Course2);
        JSONObject search_Course3 = new JSONObject();
        search_Course3.put("teacher_id", "Zenzele");
        search_Course3.put("course_code", "COMS1004");
        search_Course3.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course3);
        JSONObject search_Course4 = new JSONObject();
        search_Course4.put("teacher_id", "Zenzele");
        search_Course4.put("course_code", "COMS1004");
        search_Course4.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course4);
        JSONObject search_Course5 = new JSONObject();
        search_Course5.put("teacher_id", "Zenzele");
        search_Course5.put("course_code", "COMS1004");
        search_Course5.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course5);
        JSONObject search_Course6 = new JSONObject();
        search_Course6.put("teacher_id", "Zenzele");
        search_Course6.put("course_code", "COMS1004");
        search_Course6.put("course_name", "Basic Programming");
        search_CourseArray.put(search_Course6);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                search_.course_list.removeAllViews();
                ViewsClass.get_information(search_.getBaseContext(),"Basic Programming",search_.course_list, search_CourseArray,
                        teacherName, courseCode, courseName, userNumber);
            }
        });
        assertEquals(search_.course_list.getChildCount(), 2);
    }
    @Test
    public void backPressOpen() throws Throwable {
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                search_.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }
    @Test
    public void backPressClosed() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                search_.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @After
    public void tearDown() throws Exception {
        search_ = null;
        Intents.release();
    }
}