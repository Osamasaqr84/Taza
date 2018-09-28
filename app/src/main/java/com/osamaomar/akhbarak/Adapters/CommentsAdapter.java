package com.osamaomar.akhbarak.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.osamaomar.akhbarak.Activities.RebliesActivity;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.CommentsModel;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{

    private Context context;
  //  private List<Drawable> mList = new ArrayList<>();
    private List<CommentsModel.DataBean> mList = new ArrayList<>();
    String nameuser,commentuser;
//    public CommentsAdapter(Context activity,String name,String comment) {
//        context = activity;
//        nameuser = name;
//        commentuser = comment;
//    }
    public CommentsAdapter(Context activity,List<CommentsModel.DataBean> list) {
        context = activity;
        mList=list;
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

       // holder.userName.setText(mList.get(position)/);
        holder.commentTxt.setText(mList.get(position).getComment());
        if (!mList.get(position).getPhotoUrl().matches("")) {
            holder.commentimg.setVisibility(View.VISIBLE);
            Glide.with(context).load(mList.get(position).getPhotoUrl()).into(holder.commentimg);
        }
        holder.rebly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RebliesActivity.class);
                intent.putExtra("commentid",mList.get(position).getCommentId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        ImageView imageProfile,commentimg;
        TextView userName,commentTime,commentTxt,countLikes,countComments,rebly;
        RecyclerView recyclerView;

        public ViewHolder(View view) {

            super(view);
            mView = view;
            imageProfile = mView.findViewById(R.id.userImage);
            userName = mView.findViewById(R.id.userName);
            commentTime = mView.findViewById(R.id.commentTime);
            commentTxt = mView.findViewById(R.id.userComment);
            commentimg = mView.findViewById(R.id.comment_img);
            rebly = mView.findViewById(R.id.rebly);

        }
    }


}
