package com.tengkuhafidz.learndroid;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class QuizAttackActivity extends AppCompatActivity {
    private LinearLayout quizLayout;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1Radio;
    private RadioButton option2Radio;
    private RadioButton option3Radio;
    private RadioButton option4Radio;
    private Button submitButton;
    public TextView streakRecordText;
    private ArrayList<Quiz> quizSets;
    private Quiz quizSet;
    public static final String SHAREDPREF_STREAK = "SharedPrefCurrentStreak";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_attack);
        quizLayout = (LinearLayout) findViewById(R.id.quiz_layout);
        getSupportActionBar().setTitle("Quiz Attack");
        quizSets = getNewQuizSets(0);
        setQuestion();
        setCurrentStreak(0);
    }

    private void setQuestion() {
        quizSet = getQuizSet();

        questionTextView = (TextView) findViewById(R.id.question_text);
        option1Radio = (RadioButton) findViewById(R.id.option1_radio);
        option2Radio = (RadioButton) findViewById(R.id.option2_radio);
        option3Radio = (RadioButton) findViewById(R.id.option3_radio);
        option4Radio = (RadioButton) findViewById(R.id.option4_radio);

        questionTextView.setText(quizSet.getQuestion());
        option1Radio.setText(quizSet.getOption1());
        option2Radio.setText(quizSet.getOption2());
        option3Radio.setText(quizSet.getOption3());
        option4Radio.setText(quizSet.getOption4());

    }


    private Quiz getQuizSet() {
        int min = 0;
        int max = 9;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        Quiz quizSet = quizSets.get(randomNum);

        return quizSet;
    }

    private ArrayList<Quiz> getNewQuizSets(int level) {
        ArrayList<Quiz> quizSets = new ArrayList<Quiz>();

        switch(level){
            case 0:
                quizLayout.setBackgroundColor(Color.parseColor("#0288d1"));
                quizSets.add(new Quiz(getString(R.string.level1_question1), getString(R.string.level1_question1_option1), getString(R.string.level1_question1_option2), getString(R.string.level1_question1_option3), getString(R.string.level1_question1_option4), getString(R.string.level1_question1_option1)));
                quizSets.add(new Quiz(getString(R.string.level1_question2), getString(R.string.level1_question2_option1), getString(R.string.level1_question2_option2), getString(R.string.level1_question2_option3), getString(R.string.level1_question2_option4), getString(R.string.level1_question2_option2)));
                quizSets.add(new Quiz(getString(R.string.level1_question3), getString(R.string.level1_question3_option1), getString(R.string.level1_question3_option2), getString(R.string.level1_question3_option3), getString(R.string.level1_question3_option4), getString(R.string.level1_question3_option3)));
                quizSets.add(new Quiz(getString(R.string.level1_question4), getString(R.string.level1_question4_option1), getString(R.string.level1_question4_option2), getString(R.string.level1_question4_option3), getString(R.string.level1_question4_option4), getString(R.string.level1_question4_option4)));
                quizSets.add(new Quiz(getString(R.string.level1_question5), getString(R.string.level1_question5_option1), getString(R.string.level1_question5_option2), getString(R.string.level1_question5_option3), getString(R.string.level1_question5_option4), getString(R.string.level1_question5_option4)));
                quizSets.add(new Quiz(getString(R.string.level1_question6), getString(R.string.level1_question6_option1), getString(R.string.level1_question6_option2), getString(R.string.level1_question6_option3), getString(R.string.level1_question6_option4), getString(R.string.level1_question6_option3)));
                quizSets.add(new Quiz(getString(R.string.level1_question7), getString(R.string.level1_question7_option1), getString(R.string.level1_question7_option2), getString(R.string.level1_question7_option3), getString(R.string.level1_question7_option4), getString(R.string.level1_question7_option2)));
                quizSets.add(new Quiz(getString(R.string.level1_question8), getString(R.string.level1_question8_option1), getString(R.string.level1_question8_option2), getString(R.string.level1_question8_option3), getString(R.string.level1_question8_option4), getString(R.string.level1_question8_option1)));
                quizSets.add(new Quiz(getString(R.string.level1_question9), getString(R.string.level1_question9_option1), getString(R.string.level1_question9_option2), getString(R.string.level1_question9_option3), getString(R.string.level1_question9_option4), getString(R.string.level1_question9_option1)));
                quizSets.add(new Quiz(getString(R.string.level1_question10), getString(R.string.level1_question10_option1), getString(R.string.level1_question10_option2), getString(R.string.level1_question10_option3), getString(R.string.level1_question10_option4), getString(R.string.level1_question10_option3)));
                break;
            case 1:
                quizLayout.setBackgroundColor(Color.parseColor("#1976d2"));
                quizSets.add(new Quiz(getString(R.string.level2_question1), getString(R.string.level2_question1_option1), getString(R.string.level2_question1_option2), getString(R.string.level2_question1_option3), getString(R.string.level2_question1_option4), getString(R.string.level2_question1_option3)));
                quizSets.add(new Quiz(getString(R.string.level2_question2), getString(R.string.level2_question2_option1), getString(R.string.level2_question2_option2), getString(R.string.level2_question2_option3), getString(R.string.level2_question2_option4), getString(R.string.level2_question2_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question3), getString(R.string.level2_question3_option1), getString(R.string.level2_question3_option2), getString(R.string.level2_question3_option3), getString(R.string.level2_question3_option4), getString(R.string.level2_question3_option2)));
                quizSets.add(new Quiz(getString(R.string.level2_question4), getString(R.string.level2_question4_option1), getString(R.string.level2_question4_option2), getString(R.string.level2_question4_option3), getString(R.string.level2_question4_option4), getString(R.string.level2_question4_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question5), getString(R.string.level2_question5_option1), getString(R.string.level2_question5_option2), getString(R.string.level2_question5_option3), getString(R.string.level2_question5_option4), getString(R.string.level2_question5_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question6), getString(R.string.level2_question6_option1), getString(R.string.level2_question6_option2), getString(R.string.level2_question6_option3), getString(R.string.level2_question6_option4), getString(R.string.level2_question6_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question7), getString(R.string.level2_question7_option1), getString(R.string.level2_question7_option2), getString(R.string.level2_question7_option3), getString(R.string.level2_question7_option4), getString(R.string.level2_question7_option1)));
                quizSets.add(new Quiz(getString(R.string.level2_question8), getString(R.string.level2_question8_option1), getString(R.string.level2_question8_option2), getString(R.string.level2_question8_option3), getString(R.string.level2_question8_option4), getString(R.string.level2_question8_option1)));
                quizSets.add(new Quiz(getString(R.string.level2_question9), getString(R.string.level2_question9_option1), getString(R.string.level2_question9_option2), getString(R.string.level2_question9_option3), getString(R.string.level2_question9_option4), getString(R.string.level2_question9_option2)));
                quizSets.add(new Quiz(getString(R.string.level2_question10), getString(R.string.level2_question10_option1), getString(R.string.level2_question10_option2), getString(R.string.level2_question10_option3), getString(R.string.level2_question10_option4),  getString(R.string.level2_question10_option2)));
                break;
            case 2:
                quizLayout.setBackgroundColor(Color.parseColor("#303f9f"));
                quizSets.add(new Quiz(getString(R.string.level3_question1), getString(R.string.level3_question1_option1), getString(R.string.level3_question1_option2), getString(R.string.level3_question1_option3), getString(R.string.level3_question1_option4), getString(R.string.level3_question1_option2)));
                quizSets.add(new Quiz(getString(R.string.level3_question2), getString(R.string.level3_question2_option1), getString(R.string.level3_question2_option2), getString(R.string.level3_question2_option3), getString(R.string.level3_question2_option4), getString(R.string.level3_question2_option3)));
                quizSets.add(new Quiz(getString(R.string.level3_question3), getString(R.string.level3_question3_option1), getString(R.string.level3_question3_option2), getString(R.string.level3_question3_option3), getString(R.string.level3_question3_option5), getString(R.string.level3_question3_option2)));
                quizSets.add(new Quiz(getString(R.string.level3_question4), getString(R.string.level3_question4_option1), getString(R.string.level3_question4_option2), getString(R.string.level3_question4_option3), getString(R.string.level3_question4_option4), getString(R.string.level3_question4_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question5), getString(R.string.level3_question5_option1), getString(R.string.level3_question5_option2), getString(R.string.level3_question5_option3), getString(R.string.level3_question5_option4), getString(R.string.level3_question5_option1)));
                quizSets.add(new Quiz(getString(R.string.level3_question6), getString(R.string.level3_question6_option1), getString(R.string.level3_question6_option2), getString(R.string.level3_question6_option3), getString(R.string.level3_question6_option4), getString(R.string.level3_question6_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question7), getString(R.string.level3_question7_option1), getString(R.string.level3_question7_option2), getString(R.string.level3_question7_option3), getString(R.string.level3_question7_option4), getString(R.string.level3_question7_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question8), getString(R.string.level3_question8_option1), getString(R.string.level3_question8_option2), getString(R.string.level3_question8_option3), getString(R.string.level3_question8_option4), getString(R.string.level3_question8_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question9), getString(R.string.level3_question9_option1), getString(R.string.level3_question9_option2), getString(R.string.level3_question9_option3), getString(R.string.level3_question9_option4), getString(R.string.level3_question9_option1)));
                quizSets.add(new Quiz(getString(R.string.level3_question10), getString(R.string.level3_question10_option1), getString(R.string.level3_question10_option2), getString(R.string.level3_question10_option3), getString(R.string.level3_question10_option4), getString(R.string.level3_question10_option3)));
                break;
            case 3:
                quizLayout.setBackgroundColor(Color.parseColor("#00796b"));
                quizSets.add(new Quiz(getString(R.string.level4_question1), getString(R.string.level4_question1_option1), getString(R.string.level4_question1_option2), getString(R.string.level4_question1_option3), getString(R.string.level4_question1_option4), getString(R.string.level4_question1_option1)));
                quizSets.add(new Quiz(getString(R.string.level4_question2), getString(R.string.level4_question2_option1), getString(R.string.level4_question2_option2), getString(R.string.level4_question2_option3), getString(R.string.level4_question2_option4), getString(R.string.level4_question2_option1)));
                quizSets.add(new Quiz(getString(R.string.level4_question3), getString(R.string.level4_question3_option1), getString(R.string.level4_question3_option2), getString(R.string.level4_question3_option3), getString(R.string.level4_question3_option4), getString(R.string.level4_question3_option1)));
                quizSets.add(new Quiz(getString(R.string.level4_question4), getString(R.string.level4_question4_option1), getString(R.string.level4_question4_option2), getString(R.string.level4_question4_option3), getString(R.string.level4_question4_option4), getString(R.string.level4_question4_option2)));
                quizSets.add(new Quiz(getString(R.string.level4_question5), getString(R.string.level4_question5_option1), getString(R.string.level4_question5_option2), getString(R.string.level4_question5_option3), getString(R.string.level4_question5_option4), getString(R.string.level4_question5_option3)));
                quizSets.add(new Quiz(getString(R.string.level4_question6), getString(R.string.level4_question6_option1), getString(R.string.level4_question6_option2), getString(R.string.level4_question6_option3), getString(R.string.level4_question6_option4), getString(R.string.level4_question6_option2)));
                quizSets.add(new Quiz(getString(R.string.level4_question7), getString(R.string.level4_question7_option1), getString(R.string.level4_question7_option2), getString(R.string.level4_question7_option3), getString(R.string.level4_question7_option4), getString(R.string.level4_question7_option2)));
                quizSets.add(new Quiz(getString(R.string.level4_question8), getString(R.string.level4_question8_option1), getString(R.string.level4_question8_option2), getString(R.string.level4_question8_option3), getString(R.string.level4_question8_option4), getString(R.string.level4_question8_option3)));
                quizSets.add(new Quiz(getString(R.string.level4_question9), getString(R.string.level4_question9_option1), getString(R.string.level4_question9_option2), getString(R.string.level4_question9_option3), getString(R.string.level4_question9_option4), getString(R.string.level4_question9_option4)));
                quizSets.add(new Quiz(getString(R.string.level4_question10), getString(R.string.level4_question10_option1), getString(R.string.level4_question10_option2), getString(R.string.level4_question10_option3), getString(R.string.level4_question10_option4), getString(R.string.level4_question10_option2)));
                break;
            default:
                quizLayout.setBackgroundColor(Color.parseColor("#455a64"));
                quizSets.add(new Quiz(getString(R.string.level1_question1), getString(R.string.level1_question1_option1), getString(R.string.level1_question1_option2), getString(R.string.level1_question1_option3), getString(R.string.level1_question1_option4), getString(R.string.level1_question1_option1)));
                quizSets.add(new Quiz(getString(R.string.level1_question2), getString(R.string.level1_question2_option1), getString(R.string.level1_question2_option2), getString(R.string.level1_question2_option3), getString(R.string.level1_question2_option4), getString(R.string.level1_question2_option2)));
                quizSets.add(new Quiz(getString(R.string.level1_question3), getString(R.string.level1_question3_option1), getString(R.string.level1_question3_option2), getString(R.string.level1_question3_option3), getString(R.string.level1_question3_option4), getString(R.string.level1_question3_option3)));
                quizSets.add(new Quiz(getString(R.string.level1_question4), getString(R.string.level1_question4_option1), getString(R.string.level1_question4_option2), getString(R.string.level1_question4_option3), getString(R.string.level1_question4_option4), getString(R.string.level1_question4_option4)));
                quizSets.add(new Quiz(getString(R.string.level1_question5), getString(R.string.level1_question5_option1), getString(R.string.level1_question5_option2), getString(R.string.level1_question5_option3), getString(R.string.level1_question5_option4), getString(R.string.level1_question5_option4)));
                quizSets.add(new Quiz(getString(R.string.level1_question6), getString(R.string.level1_question6_option1), getString(R.string.level1_question6_option2), getString(R.string.level1_question6_option3), getString(R.string.level1_question6_option4), getString(R.string.level1_question6_option3)));
                quizSets.add(new Quiz(getString(R.string.level1_question7), getString(R.string.level1_question7_option1), getString(R.string.level1_question7_option2), getString(R.string.level1_question7_option3), getString(R.string.level1_question7_option4), getString(R.string.level1_question7_option2)));
                quizSets.add(new Quiz(getString(R.string.level1_question8), getString(R.string.level1_question8_option1), getString(R.string.level1_question8_option2), getString(R.string.level1_question8_option3), getString(R.string.level1_question8_option4), getString(R.string.level1_question8_option2)));
                quizSets.add(new Quiz(getString(R.string.level1_question9), getString(R.string.level1_question9_option1), getString(R.string.level1_question9_option2), getString(R.string.level1_question9_option3), getString(R.string.level1_question9_option4), getString(R.string.level1_question9_option1)));
                quizSets.add(new Quiz(getString(R.string.level1_question10), getString(R.string.level1_question10_option1), getString(R.string.level1_question10_option2), getString(R.string.level1_question10_option3), getString(R.string.level1_question10_option4), getString(R.string.level1_question10_option3)));
                quizSets.add(new Quiz(getString(R.string.level2_question1), getString(R.string.level2_question1_option1), getString(R.string.level2_question1_option2), getString(R.string.level2_question1_option3), getString(R.string.level2_question1_option4), getString(R.string.level2_question1_option3)));
                quizSets.add(new Quiz(getString(R.string.level2_question2), getString(R.string.level2_question2_option1), getString(R.string.level2_question2_option2), getString(R.string.level2_question2_option3), getString(R.string.level2_question2_option4), getString(R.string.level2_question2_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question3), getString(R.string.level2_question3_option1), getString(R.string.level2_question3_option2), getString(R.string.level2_question3_option3), getString(R.string.level2_question3_option4), getString(R.string.level2_question3_option2)));
                quizSets.add(new Quiz(getString(R.string.level2_question4), getString(R.string.level2_question4_option1), getString(R.string.level2_question4_option2), getString(R.string.level2_question4_option3), getString(R.string.level2_question4_option4), getString(R.string.level2_question4_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question5), getString(R.string.level2_question5_option1), getString(R.string.level2_question5_option2), getString(R.string.level2_question5_option3), getString(R.string.level2_question5_option4), getString(R.string.level2_question5_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question6), getString(R.string.level2_question6_option1), getString(R.string.level2_question6_option2), getString(R.string.level2_question6_option3), getString(R.string.level2_question6_option4), getString(R.string.level2_question6_option4)));
                quizSets.add(new Quiz(getString(R.string.level2_question7), getString(R.string.level2_question7_option1), getString(R.string.level2_question7_option2), getString(R.string.level2_question7_option3), getString(R.string.level2_question7_option4), getString(R.string.level2_question7_option1)));
                quizSets.add(new Quiz(getString(R.string.level2_question8), getString(R.string.level2_question8_option1), getString(R.string.level2_question8_option2), getString(R.string.level2_question8_option3), getString(R.string.level2_question8_option4), getString(R.string.level2_question8_option1)));
                quizSets.add(new Quiz(getString(R.string.level2_question9), getString(R.string.level2_question9_option1), getString(R.string.level2_question9_option2), getString(R.string.level2_question9_option3), getString(R.string.level2_question9_option4), getString(R.string.level2_question9_option2)));
                quizSets.add(new Quiz(getString(R.string.level2_question10), getString(R.string.level2_question10_option1), getString(R.string.level2_question10_option2), getString(R.string.level2_question10_option3), getString(R.string.level2_question10_option4),  getString(R.string.level2_question10_option2)));
                quizSets.add(new Quiz(getString(R.string.level3_question1), getString(R.string.level3_question1_option1), getString(R.string.level3_question1_option2), getString(R.string.level3_question1_option3), getString(R.string.level3_question1_option4), getString(R.string.level3_question1_option3)));
                quizSets.add(new Quiz(getString(R.string.level3_question2), getString(R.string.level3_question2_option1), getString(R.string.level3_question2_option2), getString(R.string.level3_question2_option3), getString(R.string.level3_question2_option4), getString(R.string.level3_question2_option3)));
                quizSets.add(new Quiz(getString(R.string.level3_question3), getString(R.string.level3_question3_option1), getString(R.string.level3_question3_option2), getString(R.string.level3_question3_option3), getString(R.string.level3_question3_option5), getString(R.string.level3_question3_option2)));
                quizSets.add(new Quiz(getString(R.string.level3_question4), getString(R.string.level3_question4_option1), getString(R.string.level3_question4_option2), getString(R.string.level3_question4_option3), getString(R.string.level3_question4_option4), getString(R.string.level3_question4_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question5), getString(R.string.level3_question5_option1), getString(R.string.level3_question5_option2), getString(R.string.level3_question5_option3), getString(R.string.level3_question5_option4), getString(R.string.level3_question5_option1)));
                quizSets.add(new Quiz(getString(R.string.level3_question6), getString(R.string.level3_question6_option1), getString(R.string.level3_question6_option2), getString(R.string.level3_question6_option3), getString(R.string.level3_question6_option4), getString(R.string.level3_question6_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question7), getString(R.string.level3_question7_option1), getString(R.string.level3_question7_option2), getString(R.string.level3_question7_option3), getString(R.string.level3_question7_option4), getString(R.string.level3_question7_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question8), getString(R.string.level3_question8_option1), getString(R.string.level3_question8_option2), getString(R.string.level3_question8_option3), getString(R.string.level3_question8_option4), getString(R.string.level3_question8_option4)));
                quizSets.add(new Quiz(getString(R.string.level3_question9), getString(R.string.level3_question9_option1), getString(R.string.level3_question9_option2), getString(R.string.level3_question9_option3), getString(R.string.level3_question9_option4), getString(R.string.level3_question9_option1)));
                quizSets.add(new Quiz(getString(R.string.level3_question10), getString(R.string.level3_question10_option1), getString(R.string.level3_question10_option2), getString(R.string.level3_question10_option3), getString(R.string.level3_question10_option4), getString(R.string.level3_question10_option3)));
                quizSets.add(new Quiz(getString(R.string.level4_question1), getString(R.string.level4_question1_option1), getString(R.string.level4_question1_option2), getString(R.string.level4_question1_option3), getString(R.string.level4_question1_option4), getString(R.string.level4_question1_option1)));
                quizSets.add(new Quiz(getString(R.string.level4_question2), getString(R.string.level4_question2_option1), getString(R.string.level4_question2_option2), getString(R.string.level4_question2_option3), getString(R.string.level4_question2_option4), getString(R.string.level4_question2_option1)));
                quizSets.add(new Quiz(getString(R.string.level4_question3), getString(R.string.level4_question3_option1), getString(R.string.level4_question3_option2), getString(R.string.level4_question3_option3), getString(R.string.level4_question3_option4), getString(R.string.level4_question3_option1)));
                quizSets.add(new Quiz(getString(R.string.level4_question4), getString(R.string.level4_question4_option1), getString(R.string.level4_question4_option2), getString(R.string.level4_question4_option3), getString(R.string.level4_question4_option4), getString(R.string.level4_question4_option2)));
                quizSets.add(new Quiz(getString(R.string.level4_question5), getString(R.string.level4_question5_option1), getString(R.string.level4_question5_option2), getString(R.string.level4_question5_option3), getString(R.string.level4_question5_option4), getString(R.string.level4_question5_option3)));
                quizSets.add(new Quiz(getString(R.string.level4_question6), getString(R.string.level4_question6_option1), getString(R.string.level4_question6_option2), getString(R.string.level4_question6_option3), getString(R.string.level4_question6_option4), getString(R.string.level4_question6_option2)));
                quizSets.add(new Quiz(getString(R.string.level4_question7), getString(R.string.level4_question7_option1), getString(R.string.level4_question7_option2), getString(R.string.level4_question7_option3), getString(R.string.level4_question7_option4), getString(R.string.level4_question7_option2)));
                quizSets.add(new Quiz(getString(R.string.level4_question8), getString(R.string.level4_question8_option1), getString(R.string.level4_question8_option2), getString(R.string.level4_question8_option3), getString(R.string.level4_question8_option4), getString(R.string.level4_question8_option3)));
                quizSets.add(new Quiz(getString(R.string.level4_question9), getString(R.string.level4_question9_option1), getString(R.string.level4_question9_option2), getString(R.string.level4_question9_option3), getString(R.string.level4_question9_option4), getString(R.string.level4_question9_option4)));
                quizSets.add(new Quiz(getString(R.string.level4_question10), getString(R.string.level4_question10_option1), getString(R.string.level4_question10_option2), getString(R.string.level4_question10_option3), getString(R.string.level4_question10_option4), getString(R.string.level4_question10_option2)));
                quizSets.add(new Quiz(getString(R.string.level5_question1), getString(R.string.level5_question1_option1), getString(R.string.level5_question1_option2), getString(R.string.level5_question1_option3), getString(R.string.level5_question1_option4), getString(R.string.level5_question1_option4)));
        }

        return quizSets;
    }

    public void checkAnswer(View view) {

        String correctAnswer = quizSet.getAnswer();

        optionsRadioGroup = (RadioGroup) findViewById(R.id.options_radio_group);
        int optionChosenId = optionsRadioGroup.getCheckedRadioButtonId();
        RadioButton chosenRadioButton = (RadioButton) findViewById(optionChosenId);
        if(chosenRadioButton != null) {
            String chosenAnswer = chosenRadioButton.getText().toString();

            if (chosenAnswer.equals(correctAnswer)) {
                correctAnswerAction();
            } else {
                wrongAnswerAction();
            }
        }

    }

    public void correctAnswerAction() {
        int currStreak = getCurrentStreak();


        int updatedStreak = currStreak + 1;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Nice! Next level.");


        alertDialogBuilder.setNegativeButton("leggo.",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        if(updatedStreak%5 == 0) {
            int level = updatedStreak / 5;
            quizSets = getNewQuizSets(level);


            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


        setCurrentStreak(updatedStreak);


        Toast.makeText(this, " " + updatedStreak, Toast.LENGTH_SHORT).show();


        setQuestion();

    }

    public int getCurrentStreak() {


        SharedPreferences streakPrefs = getSharedPreferences(SHAREDPREF_STREAK, MODE_PRIVATE);
        int currStreak = streakPrefs.getInt("currentStreak", 0);
        return currStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        SharedPreferences.Editor editor =
                getSharedPreferences(SHAREDPREF_STREAK, MODE_PRIVATE).edit();
        editor.putInt("currentStreak", currentStreak);
        editor.commit();
        updateRecordText(currentStreak);
    }



    public void updateRecordText(int currentStreak) {
        streakRecordText = (TextView) findViewById(R.id.streak_record_text);
        streakRecordText.setText("Current Streak: " + currentStreak);
    }

    public void wrongAnswerAction() {
        setCurrentStreak(0);
        quizSets = getNewQuizSets(0);
        Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Wrong! restart~");


        alertDialogBuilder.setNegativeButton("Sed. Okai.",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void promptQuitConfirmation() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Suare wanna quit? Progress won't be saved~");


        alertDialogBuilder.setPositiveButton("ye. Okai.",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("kensel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                promptQuitConfirmation();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        // do something here and don't write super.onBackPressed()
        promptQuitConfirmation();

    }

}
