package com.tengkuhafidz.learndroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tengkuhafidz on 9/11/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    ArrayList<Topic> mTopics;
    Context mContext;

    TopicAdapter(ArrayList<Topic> topics, Context context){
        mTopics = topics;
        mContext = context;

    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView topicTitleText;
        TextView topicIsCompletedText;
        String topicTitle;
        View view;

        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            return cm.getActiveNetworkInfo() != null;
        }

        TopicViewHolder(final View itemView) {
            super(itemView);
            view = itemView;
            cv = (CardView)itemView.findViewById(R.id.topic_card);
            topicTitleText = (TextView)itemView.findViewById(R.id.topic_title_text);
            topicIsCompletedText = (TextView)itemView.findViewById(R.id.topic_is_completed_text);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(isNetworkConnected()) {
                        Intent intent = new Intent(cv.getContext(), SectionsActivity.class);
                        intent.putExtra("topicTitle", topicTitle);
                        cv.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(itemView.getContext(), "Internet connection is required. Please turn it on to process.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topics_list_item, viewGroup, false);
        TopicViewHolder pvh = new TopicViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TopicViewHolder topicViewHolder, int i) {

        String isCompletedStr = "\u2022";
        if (mTopics.get(i).getIsCompleted()) {
            isCompletedStr = "\u2713";
        }
        topicViewHolder.topicTitleText.setText(mTopics.get(i).getTitle());
        topicViewHolder.topicIsCompletedText.setText(isCompletedStr);
        topicViewHolder.topicTitle = mTopics.get(i).getTitle();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
