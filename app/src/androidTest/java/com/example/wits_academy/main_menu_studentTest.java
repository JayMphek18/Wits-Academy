package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerActions.close;
import static androidx.test.espresso.contrib.DrawerActions.closeDrawer;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.contrib.DrawerActions.openDrawer;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Map;





@MediumTest
@RunWith(AndroidJUnit4.class)
//@RunWith(RobolectricTestRunner.class)
public class main_menu_studentTest {

    @Rule
    public ActivityTestRule<main_menu_student> mActivityRule = new ActivityTestRule(main_menu_student.class);
    private main_menu_student student;
    private String userNumber ;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        student = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = student.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav);
        menu = navigationView.getMenu();
        userNumber = student.userNumber = "1234567";
        Intents.init();
    }

    @Test
    public void addingTwoCoursesCoursesInPage() throws Throwable {
        TextView noCourse = student.findViewById(R.id.NoCoursesAvailable);

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
                noCourse.setVisibility(View.GONE);
//                student.course_list.removeAllViews();
                ViewsClass.add_layout(2, 0, student.getBaseContext() , courseName, courseCode, teacherName, student.course_list, userNumber);
            }
        });
        assertEquals(student.course_list.getChildCount(), 4);
    }
    @Test
    public void addingOneCoursesCoursesInPage() throws Throwable {
        TextView noCourse = student.findViewById(R.id.NoCoursesAvailable);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();
        courseCode.add("MATH1001");
        courseName.add("Basic Analysis");
        teacherName.add("Sethembile");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                noCourse.setVisibility(View.GONE);
//                student.course_list.removeAllViews();
                ViewsClass.add_layout(1, 0, student.getBaseContext() , courseName, courseCode, teacherName, student.course_list, userNumber);
            }
        });
        assertEquals(student.course_list.getChildCount(), 4);
    }

    @Test
    public void AddOddNumberOfCoursesStartingFromJSONArray() throws Throwable {
        TextView noCourse = student.findViewById(R.id.NoCoursesAvailable);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray studentCourseArray = new JSONArray();
        JSONObject studentCourse1 = new JSONObject();
        studentCourse1.put("teacher_id", "Sethembile");
        studentCourse1.put("course_code", "MATH1002");
        studentCourse1.put("course_name", "Basic Analysis");
        studentCourseArray.put(studentCourse1);
        JSONObject studentCourse2 = new JSONObject();
        studentCourse2.put("teacher_id", "Martin");
        studentCourse2.put("course_code", "STAT1002");
        studentCourse2.put("course_name", "Intro to Stats");
        studentCourseArray.put(studentCourse2);
        JSONObject studentCourse3 = new JSONObject();
        studentCourse3.put("teacher_id", "Zenzele");
        studentCourse3.put("course_code", "COMS1004");
        studentCourse3.put("course_name", "Basic Programming");
        studentCourseArray.put(studentCourse3);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                noCourse.setVisibility(View.GONE);
//                student.course_list.removeAllViews();
                ViewsClass.get_information_on_JSON(student.getBaseContext(),userNumber ,student.course_list, studentCourseArray,
                        teacherName, courseCode, courseName);
            }
        });

        assertEquals(student.course_list.getChildCount(), 5);
    }


    @Test
    public void AddEvenNumberOfCoursesStartingFromJSONArray() throws Throwable {
        TextView noCourse = student.findViewById(R.id.NoCoursesAvailable);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray studentCourseArray = new JSONArray();
        JSONObject studentCourse1 = new JSONObject();
        studentCourse1.put("teacher_id", "Sethembile");
        studentCourse1.put("course_code", "MATH1002");
        studentCourse1.put("course_name", "Basic Analysis");
        studentCourseArray.put(studentCourse1);
        JSONObject studentCourse2 = new JSONObject();
        studentCourse2.put("teacher_id", "Martin");
        studentCourse2.put("course_code", "STAT1002");
        studentCourse2.put("course_name", "Intro to Stats");
        studentCourseArray.put(studentCourse2);
        JSONObject studentCourse3 = new JSONObject();
        studentCourse3.put("teacher_id", "Zenzele");
        studentCourse3.put("course_code", "COMS1004");
        studentCourse3.put("course_name", "Basic Programming");
        studentCourseArray.put(studentCourse3);
        JSONObject studentCourse4 = new JSONObject();
        studentCourse4.put("teacher_id", "Zenzele");
        studentCourse4.put("course_code", "COMS1004");
        studentCourse4.put("course_name", "Basic Programming");
        studentCourseArray.put(studentCourse4);
        JSONObject studentCourse5 = new JSONObject();
        studentCourse5.put("teacher_id", "Zenzele");
        studentCourse5.put("course_code", "COMS1004");
        studentCourse5.put("course_name", "Basic Programming");
        studentCourseArray.put(studentCourse5);
        JSONObject studentCourse6 = new JSONObject();
        studentCourse6.put("teacher_id", "Zenzele");
        studentCourse6.put("course_code", "COMS1004");
        studentCourse6.put("course_name", "Basic Programming");
        studentCourseArray.put(studentCourse6);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                noCourse.setVisibility(View.GONE);
//                student.course_list.removeAllViews();
                ViewsClass.get_information_on_JSON(student.getBaseContext(),userNumber ,student.course_list, studentCourseArray,
                        teacherName, courseCode, courseName);
                assertEquals(student.course_list.getChildCount(), 6);
                View view = student.course_list.getChildAt(student.course_list.getChildCount() - 1).findViewById(R.id.lay);
                view.performClick();
            }
        });



    }

    @Test
    public void testMenuItems(){
        for (int i = 0 ; i < 4; i++){
            MenuItem menuItem = menu.getItem(i);
            assertNotNull(menuItem);
        }
    }

    @Test
    public void backPressOpen() throws Throwable {
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                student.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }
    @Test
    public void backPressClosed() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                student.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void noCoursesDisplayed(){
        TextView noCourse = student.findViewById(R.id.NoCoursesAvailable);
        assertEquals(noCourse.getVisibility(), View.VISIBLE);
        student.go_to_courses(noCourse);
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
    public void Test() throws Exception{
        assertNotNull(student.course_list);
        assertNotNull(student.drawerLayout);
        assertNotNull(student.display);
        assertNotNull(student.navigationView);
        assertNotNull(student.view);
        assertNotNull(student.userNumber);
    }
    @Test
    public void clickedMenuItem3(){
        MenuItem menuItem = menu.getItem(3);
        assertTrue(student.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem2(){
        MenuItem menuItem = menu.getItem(2);
        assertTrue(student.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem1(){
        MenuItem menuItem = menu.getItem(1);
        assertTrue(student.onNavigationItemSelected(menuItem));
    }
    @Test
    public void clickedMenuItem0(){
        MenuItem menuItem = menu.getItem(0);
        assertTrue(student.onNavigationItemSelected(menuItem));
    }

    @After
    public void tearDown() throws Exception {
        student = null;
        Intents.release();
    }
    
}