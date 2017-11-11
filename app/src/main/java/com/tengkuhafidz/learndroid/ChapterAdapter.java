package com.tengkuhafidz.learndroid;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tengkuhafidz on 9/11/17.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    ArrayList<Chapter> mChapters;

    ChapterAdapter(ArrayList<Chapter> chapters){
        mChapters = chapters;
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView chapterNumText;
        TextView chapterTitleText;
        TextView chapterPercentageCompletedText;
        ImageView chapterImage;
        String chapterTitle;

        ChapterViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.chapter_card);
            chapterNumText = (TextView)itemView.findViewById(R.id.chapter_num_text);
            chapterTitleText = (TextView)itemView.findViewById(R.id.chapter_title_text);
            chapterPercentageCompletedText = (TextView)itemView.findViewById(R.id.chapter_percentage_completed_text);
            chapterImage = (ImageView)itemView.findViewById(R.id.chapter_image);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(cv.getContext(), TopicsActivity.class);
                    intent.putExtra("chapterTitle", chapterTitle);
                    cv.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mChapters.size();
    }

    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chapters_list_item, viewGroup, false);
        ChapterViewHolder pvh = new ChapterViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ChapterViewHolder chapterViewHolder, int i) {
        int chapterNumInt = i + 1;
        chapterViewHolder.chapterNumText.setText("Chapter " + chapterNumInt);
        chapterViewHolder.chapterTitleText.setText(mChapters.get(i).getTitle());
        chapterViewHolder.chapterPercentageCompletedText.setText(mChapters.get(i).getPercentageCompleted() + "%");
        chapterViewHolder.chapterImage.setImageResource(mChapters.get(i).getImageResourceId());
        chapterViewHolder.chapterTitle = mChapters.get(i).getTitle();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
