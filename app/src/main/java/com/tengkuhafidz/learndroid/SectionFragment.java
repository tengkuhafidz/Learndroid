package com.tengkuhafidz.learndroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends Fragment {

    private YouTubePlayer YPlayer;
    private static final String YOUTUBE_API_KEY = YoutubeConfig.getAPIKey();

    public static SectionFragment newInstance(Section section, int pageNum, int totalPages) {
        SectionFragment fragment = new SectionFragment();

        String title = section.getTitle();
        String videoId = section.getVideoId();
        String lessonText = section.getLessonText();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("videoId", videoId);
        args.putString("lessonText", lessonText);
        args.putInt("pageNum", pageNum);
        args.putInt("totalPages", totalPages);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_section, container, false);
        String title = getArguments().getString("title");
        final String videoId = getArguments().getString("videoId");
        String lessonText = getArguments().getString("lessonText");
        int pageNum = getArguments().getInt("pageNum", 0);
        int totalPages = getArguments().getInt("totalPages", 0);
        String pageIndicator = pageNum + " / " + totalPages;


        TextView lessonTextView = (TextView) rootView.findViewById(R.id.section_lesson_text);
        lessonTextView.setText(lessonText);

        TextView pageTextView = (TextView) rootView.findViewById(R.id.section_page_text);
        pageTextView.setText(pageIndicator);


        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    YPlayer = youTubePlayer;
                    YPlayer.loadVideo(videoId);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                // TODO Auto-generated method stub

            }
        });





        return rootView;
    }
}
