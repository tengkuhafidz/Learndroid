package com.tengkuhafidz.learndroid;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    public TextView streakRecordText;
    private Quiz quizSet;
    private ArrayList<Quiz> quizSets;
    private int score = 0;
    private int highscore;
    int level = 1;
    private boolean highscoreBroken = false;
    public static final String SHAREDPREF_SCORE = "SharedPrefScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_attack);
        // reference to layout and views
        quizLayout = (LinearLayout) findViewById(R.id.quiz_layout);
        questionTextView = (TextView) findViewById(R.id.question_text);
        option1Radio = (RadioButton) findViewById(R.id.option1_radio);
        option2Radio = (RadioButton) findViewById(R.id.option2_radio);
        option3Radio = (RadioButton) findViewById(R.id.option3_radio);
        option4Radio = (RadioButton) findViewById(R.id.option4_radio);
        // set action bar title
        getSupportActionBar().setTitle("Quiz Attack");
        //prepare quiz set - starting with level 1 (index of 0)
        quizSets = getNewQuizSets(level);
        setQuestion();
        highscore = getHighscore();
    }

    // prepare quiz set
    private void setQuestion() {
        quizSet = getQuizSet();
        questionTextView.setText(quizSet.getQuestion());
        option1Radio.setText(quizSet.getOption1());
        option2Radio.setText(quizSet.getOption2());
        option3Radio.setText(quizSet.getOption3());
        option4Radio.setText(quizSet.getOption4());
    }

    // return a random set of quiz
    private Quiz getQuizSet() {
        int min = 0;
        int max = 9;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        Quiz quizSet = quizSets.get(randomNum);
        return quizSet;
    }


    // handle user's answer
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

    // update current streak score,
    // handle highscore check,
    // check for level up, and
    // check for infinity more
    public void correctAnswerAction() {
        score++;
        // update highscore if needed
        if (!highscoreBroken && score > highscore) {
            setHighscore(score);
            popDialog("Wooop! Beaten your own highscore. All the way now!", null, "Okay");

            highscoreBroken = true;
        }
        // set to infinity mode when when needed
        if (level != -1) {
            if (score < 20 && score % 5 == 0) {
                level++;
            } else if (score == 20) {
                level = -1;
                popDialog("WOAH! You made it to infinity mode. Gain as many scores as you can!", null, "Okay");
            }
            getNewQuizSets(level);
        }
        // prepare quiz set
        setQuestion();

        //update record text
        updateRecordText();
    }



    //helper method to handle dialog
    public void popDialog(String message, String positiveMessage, String negativeMessage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);

        if (positiveMessage != null) {
            alertDialogBuilder.setPositiveButton(positiveMessage, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }

        if (negativeMessage != null) {
            alertDialogBuilder.setNegativeButton(negativeMessage, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public int getHighscore() {
        SharedPreferences scorePrefs = getSharedPreferences(SHAREDPREF_SCORE, MODE_PRIVATE);
        return scorePrefs.getInt("highscore", 0);
    }

    public void setHighscore(int score) {
        SharedPreferences.Editor editor =
                getSharedPreferences(SHAREDPREF_SCORE, MODE_PRIVATE).edit();
        editor.putInt("highscore", score);
        editor.commit();
    }



    public void updateRecordText() {
        streakRecordText = (TextView) findViewById(R.id.streak_record_text);
        if(level != -1) {
            streakRecordText.setText("Level: " + level + "  |  Score: " + score);
        } else {
            streakRecordText.setText("INFINITY MODE  |  Score: " + score);
        }
    }

    public void wrongAnswerAction() {
        score = 0;
        quizSets = getNewQuizSets(0);

        popDialog("Oooops, you got that wrong. Restart!", "Okay", null);

    }

    private void promptQuitConfirmation() {
        popDialog("Quit game now? Your cannot resume your play later.", "Confirm", "Cancel");
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

    private ArrayList<Quiz> getNewQuizSets(int level) {
        ArrayList<Quiz> quizSets = new ArrayList<Quiz>();
        switch(level){
            case 1:
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
            case 2:
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
            case 3:
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
            case 4:
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

}
