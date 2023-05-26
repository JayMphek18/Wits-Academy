package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
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
public class main_menu_teacherTest {

    @Rule
    public ActivityTestRule<main_menu_teacher> mActivityRule = new ActivityTestRule(main_menu_teacher.class);
    private main_menu_teacher teacher;
    private static final int[] MENU_CONTENT_ITEM_IDS = {R.id.search, R.id.profile, R.id.logout, R.id.menu_page};
    private String userNumber;
    private Map<Integer, String> menuStringContent;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Menu menu;

    @Before
    public void setUp() throws Exception {
        teacher = mActivityRule.getActivity();
//        final NavigationViewActivity activity = mActivityRule.getActivity();
        drawerLayout = teacher.findViewById(R.id.draw_layout);
        navigationView = drawerLayout.findViewById(R.id.nav);
        menu = navigationView.getMenu();
        userNumber = teacher.userNumber = "1234567";
        Intents.init();
    }

    @Test
    public void addingTwoCoursesCoursesInPage() throws Throwable {
//        TextView noCourse = teacher.findViewById(R.id.NoCoursesAvailable);

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
//                noCourse.setVisibility(View.GONE);
//                teacher.course_list.removeAllViews();
                ViewsClass.add_layout(2, 0, teacher.getBaseContext() , courseName, courseCode, teacherName, teacher.course_list, userNumber);
            }
        });
        assertEquals(teacher.course_list.getChildCount(), 3);
    }

    @Test
    public void addingOneCoursesCoursesInPage() throws Throwable {
//        TextView noCourse = teacher.findViewById(R.id.NoCoursesAvailable);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();
        courseCode.add("MATH1001");
        courseName.add("Basic Analysis");
        teacherName.add("Sethembile");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                noCourse.setVisibility(View.GONE);
//                teacher.course_list.removeAllViews();
                ViewsClass.add_layout(1, 0, teacher.getBaseContext() , courseName, courseCode, teacherName, teacher.course_list, userNumber);
            }
        });
        assertEquals(teacher.course_list.getChildCount(), 3);
    }

    @Test
    public void AddOddNumberOfCoursesStartingFromJSONArray() throws Throwable {
//        TextView noCourse = teacher.findViewById(R.id.NoCoursesAvailable);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray teacherCourseArray = new JSONArray();
        JSONObject teacherCourse1 = new JSONObject();
        teacherCourse1.put("teacher_id", "Sethembile");
        teacherCourse1.put("course_code", "MATH1002");
        teacherCourse1.put("course_name", "Basic Analysis");
        teacherCourseArray.put(teacherCourse1);
        JSONObject teacherCourse2 = new JSONObject();
        teacherCourse2.put("teacher_id", "Martin");
        teacherCourse2.put("course_code", "STAT1002");
        teacherCourse2.put("course_name", "Intro to Stats");
        teacherCourseArray.put(teacherCourse2);
        JSONObject teacherCourse3 = new JSONObject();
        teacherCourse3.put("teacher_id", "Zenzele");
        teacherCourse3.put("course_code", "COMS1004");
        teacherCourse3.put("course_name", "Basic Programming");
        teacherCourseArray.put(teacherCourse3);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                noCourse.setVisibility(View.GONE);
//                teacher.course_list.removeAllViews();
                ViewsClass.get_information_on_JSON(teacher.getBaseContext(),userNumber ,teacher.course_list, teacherCourseArray,
                        teacherName, courseCode, courseName);
            }
        });

        assertEquals(teacher.course_list.getChildCount(), 4);
    }


    @Test
    public void AddEvenNumberOfCoursesStartingFromJSONArray() throws Throwable {
//        TextView noCourse = teacher.findViewById(R.id.NoCoursesAvailable);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> teacherName = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();

        JSONArray teacherCourseArray = new JSONArray();
        JSONObject teacherCourse1 = new JSONObject();
        teacherCourse1.put("teacher_id", "Sethembile");
        teacherCourse1.put("course_code", "MATH1002");
        teacherCourse1.put("course_name", "Basic Analysis");
        teacherCourseArray.put(teacherCourse1);
        JSONObject teacherCourse2 = new JSONObject();
        teacherCourse2.put("teacher_id", "Martin");
        teacherCourse2.put("course_code", "STAT1002");
        teacherCourse2.put("course_name", "Intro to Stats");
        teacherCourseArray.put(teacherCourse2);
        JSONObject teacherCourse3 = new JSONObject();
        teacherCourse3.put("teacher_id", "Zenzele");
        teacherCourse3.put("course_code", "COMS1004");
        teacherCourse3.put("course_name", "Basic Programming");
        teacherCourseArray.put(teacherCourse3);
        JSONObject teacherCourse4 = new JSONObject();
        teacherCourse4.put("teacher_id", "Zenzele");
        teacherCourse4.put("course_code", "COMS1004");
        teacherCourse4.put("course_name", "Basic Programming");
        teacherCourseArray.put(teacherCourse4);
        JSONObject teacherCourse5 = new JSONObject();
        teacherCourse5.put("teacher_id", "Zenzele");
        teacherCourse5.put("course_code", "COMS1004");
        teacherCourse5.put("course_name", "Basic Programming");
        teacherCourseArray.put(teacherCourse5);
        JSONObject teacherCourse6 = new JSONObject();
        teacherCourse6.put("teacher_id", "Zenzele");
        teacherCourse6.put("course_code", "COMS1004");
        teacherCourse6.put("course_name", "Basic Programming");
        teacherCourseArray.put(teacherCourse6);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                noCourse.setVisibility(View.GONE);
//                teacher.course_list.removeAllViews();
                ViewsClass.get_information_on_JSON(teacher.getBaseContext(),userNumber ,teacher.course_list, teacherCourseArray,
                        teacherName, courseCode, courseName);
            }
        });
        assertEquals(teacher.course_list.getChildCount(), 5);
    }
    @Test
    public void backPressOpen() throws Throwable {
        onView(withId(R.id.draw_layout)).perform(open(GravityCompat.START));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                teacher.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }
    @Test
    public void backPressClosed() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                teacher.onBackPressed();
            }
        });
        assertFalse(navigationView.isActivated());
    }

    @Test
    public void testMenuItems(){
        for (int i = 0 ; i < 4; i++){
            MenuItem menuItem = menu.getItem(i);
            assertNotNull(menuItem);
        }
    }

//    @Test
//    public void noCoursesDisplayed(){
//        TextView noCourse = teacher.findViewById(R.id.NoCoursesAvailable);
//        assertEquals(noCourse.getVisibility(), View.VISIBLE);
//    }

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
    @Test
    public void Test() throws Exception{
        assertNotNull(teacher.course_list);
        assertNotNull(teacher.drawerLayout);
        assertNotNull(teacher.navigationView);
        assertNotNull(teacher.userNumber);
    }


    @After
    public void tearDown() throws Exception {
        teacher = null;
        Intents.release();
    }

}