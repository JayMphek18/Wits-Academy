package com.example.wits_academy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.*;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class write_quizTest {

    @Rule
    public ActivityTestRule<write_quiz> mActivityRule = new ActivityTestRule(write_quiz.class);
    private write_quiz write;
    String userNumber;
    String courseName;
    String quizName;
    LinearLayout layout;

    @Before
    public void setUp() throws Exception {
        write = mActivityRule.getActivity();
        userNumber = write.userNumber = "1234567";
        courseName = write.courseName = "Advance Analysis";
        quizName = write.quizName = "Quiz 1";
        layout = write.findViewById(R.id.quiz);
    }

    @Test
    public void addInputQuestionToView() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1");
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"How many countries are in ASIA","5", "1");
            }
        });
        assertEquals(2, layout.getChildCount());
    }

    @Test
    public void addMultipleChoiceQuestionToView() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1", "Europe", "South America", "North America", "Africa");
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"How many countries are in ASIA",
                        "5", "1", "15", "55", "71", "None of the above");
            }
        });
        assertEquals(2, layout.getChildCount());
    }
    @Test
    public void answerQuestionsOptionA() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1", "Europe", "South America", "North America", "Africa");
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"How many countries are in ASIA","5", "1");

            }
        });
        assertEquals(2, layout.getChildCount());

        View add_question = layout.getChildAt(0);
        View input = layout.getChildAt(1);
        EditText answer = input.findViewById(R.id.enter_answer);
       // answer.setText("55");
        RadioButton choiceA = add_question.findViewById(R.id.optionA);
        RadioButton choiceB = add_question.findViewById(R.id.optionB);
        RadioButton choiceC = add_question.findViewById(R.id.optionC);
        RadioButton choiceD = add_question.findViewById(R.id.optionD);

        choiceA.setChecked(true);
        choiceB.setChecked(false);
        choiceC.setChecked(false);
        choiceD.setChecked(false);

        Button button = write.findViewById(R.id.button);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
    }

    @Test
    public void answerQuestionsOptionB() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1", "Europe", "South America", "North America", "Africa");
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"How many countries are in ASIA","5", "1");

            }
        });
        assertEquals(2, layout.getChildCount());

        View add_question = layout.getChildAt(0);
        View input = layout.getChildAt(1);
        EditText answer = input.findViewById(R.id.enter_answer);
        //answer.setText("55");
        RadioButton choiceA = add_question.findViewById(R.id.optionA);
        RadioButton choiceB = add_question.findViewById(R.id.optionB);
        RadioButton choiceC = add_question.findViewById(R.id.optionC);
        RadioButton choiceD = add_question.findViewById(R.id.optionD);

        choiceA.setChecked(false);
        choiceB.setChecked(true);
        choiceC.setChecked(false);
        choiceD.setChecked(false);

        Button button = write.findViewById(R.id.button);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });    }

    @Test
    public void answerQuestionsOptionC() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1", "Europe", "South America", "North America", "Africa");
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"How many countries are in ASIA","5", "1");

            }
        });
        assertEquals(2, layout.getChildCount());

        View add_question = layout.getChildAt(0);
        View input = layout.getChildAt(1);
        EditText answer = input.findViewById(R.id.enter_answer);
       // answer.setText("55");
        RadioButton choiceA = add_question.findViewById(R.id.optionA);
        RadioButton choiceB = add_question.findViewById(R.id.optionB);
        RadioButton choiceC = add_question.findViewById(R.id.optionC);
        RadioButton choiceD = add_question.findViewById(R.id.optionD);

        choiceA.setChecked(false);
        choiceB.setChecked(false);
        choiceC.setChecked(true);
        choiceD.setChecked(false);

        Button button = write.findViewById(R.id.button);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
    }
    @Test
    public void answerQuestionsOptionD() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1", "Europe", "South America", "North America", "Africa");
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"How many countries are in ASIA","5", "1");

            }
        });
        assertEquals(2, layout.getChildCount());

        View add_question = layout.getChildAt(0);
        View input = layout.getChildAt(1);

        EditText answer = input.findViewById(R.id.enter_answer);
       // answer.setText("55");

        RadioButton choiceA = add_question.findViewById(R.id.optionA);
        RadioButton choiceB = add_question.findViewById(R.id.optionB);
        RadioButton choiceC = add_question.findViewById(R.id.optionC);
        RadioButton choiceD = add_question.findViewById(R.id.optionD);



        Button button = write.findViewById(R.id.button);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                choiceA.setChecked(false);

        choiceB.setChecked(false);

        choiceC.setChecked(false);

        choiceD.setChecked(true);
                button.performClick();
            }
        });
    }

    @Test
    public void notAllAnswered() throws Throwable {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewsClass.add_multiple_choice(write.getApplicationContext(), layout,"Where in the world will you find south africa\n\n" +
                        "HINT : In terms of continents.","5", "1", "Europe", "South America", "North America", "Africa");
                ViewsClass.add_input_question(write.getApplicationContext(), layout,"How many countries are in ASIA","5", "1");

            }
        });
        assertEquals(2, layout.getChildCount());

        View add_question = layout.getChildAt(0);
        View input = layout.getChildAt(1);

        EditText answer = input.findViewById(R.id.enter_answer);
        answer.setText("55");

        RadioButton choiceA = add_question.findViewById(R.id.optionA);
        RadioButton choiceB = add_question.findViewById(R.id.optionB);
        RadioButton choiceC = add_question.findViewById(R.id.optionC);
        RadioButton choiceD = add_question.findViewById(R.id.optionD);

        

        Button button = write.findViewById(R.id.button);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
    }


    @After
    public void tearDown() throws Exception {
        write = null;
    }
}
