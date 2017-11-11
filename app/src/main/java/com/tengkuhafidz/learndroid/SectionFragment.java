package com.tengkuhafidz.learndroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends Fragment {


    public static SectionFragment newInstance(Section section, int pageNum, int totalPages) {
        SectionFragment fragment = new SectionFragment();

        String title = section.getTitle();
        String videoUrl = section.getVideoUrl();
        String lessonText = section.getLessonText();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("videoUrl", videoUrl);
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
        String videoUrl = getArguments().getString("videoUrl");
        String lessonText = getArguments().getString("lessonText");
        int pageNum = getArguments().getInt("pageNum", 0);
        int totalPages = getArguments().getInt("totalPages", 0);
        String pageIndicator = pageNum + " / " + totalPages;


        TextView lessonTextView = (TextView) rootView.findViewById(R.id.section_lesson_text);
        lessonTextView.setText(lessonText);

        TextView pageTextView = (TextView) rootView.findViewById(R.id.section_page_text);
        pageTextView.setText(pageIndicator);

        return rootView;
    }
}
