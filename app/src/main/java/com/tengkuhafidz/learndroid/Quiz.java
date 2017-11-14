package com.tengkuhafidz.learndroid;

/**
 * Created by tengkuhafidz on 12/11/17.
 */

public class Quiz {
    private String mQuestion;
    private String mOption1;
    private String mOption2;
    private String mOption3;
    private String mOption4;
    private String mAnswer;

    Quiz(String question, String option1, String option2, String option3, String option4, String answer) {
        mQuestion = question;
        mOption1 = option1;
        mOption2 = option2;
        mOption3 = option3;
        mOption4 = option4;
        mAnswer = answer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getOption1() {
        return mOption1;
    }

    public String getOption2() {
        return mOption2;
    }

    public String getOption3() {
        return mOption3;
    }

    public String getOption4() {
        return mOption4;
    }

    public String getAnswer() {
        return mAnswer;
    }
}
