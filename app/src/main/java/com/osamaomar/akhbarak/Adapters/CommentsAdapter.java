package com.osamaomar.akhbarak.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osamaomar.akhbarak.R;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{

    private Context context;
    private List<Drawable> mList = new ArrayList<>();
    String nameuser,commentuser;
    public CommentsAdapter(Context activity,String name,String comment) {
        context = activity;
        nameuser = name;
        commentuser = comment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.userName.setText(nameuser);
        holder.commentTxt.setText(commentuser);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;

        ImageView imageProfile;
        TextView userName,commentTime,commentTxt,countLikes,countComments;
        RecyclerView recyclerView;

        public ViewHolder(View view) {

            super(view);
            mView = view;
            imageProfile = mView.findViewById(R.id.userImage);
            userName = mView.findViewById(R.id.userName);
            commentTime = mView.findViewById(R.id.commentTime);
            commentTxt = mView.findViewById(R.id.userComment);

        }
    }


}
