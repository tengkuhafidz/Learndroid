package com.tengkuhafidz.learndroid;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    Button startQuizButton;
    TextView highscoreTextView;
    public static final String SHAREDPREF_SCORE = "SharedPrefScore";


    public static QuizFragment newInstance() {
        QuizFragment fragment = new QuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        startQuizButton = (Button) rootView.findViewById(R.id.start_quiz_button);
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QuizAttackActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
       });


        highscoreTextView = (TextView) rootView.findViewById(R.id.highscore_text);
        highscoreTextView.setText("Your Highscore: " + getHighscore());

     return rootView;
    }

    public int getHighscore() {
        SharedPreferences scorePrefs = getContext().getSharedPreferences(SHAREDPREF_SCORE, MODE_PRIVATE);
        return scorePrefs.getInt("highscore", 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        highscoreTextView.setText("Your Highscore: " + getHighscore());
    }

}
