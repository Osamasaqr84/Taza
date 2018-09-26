package com.osamaomar.akhbarak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.PostDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class PostDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private List<PostDetailsModel> mList = new ArrayList<>();
    String name = "Mohamed jaber jabarin";
    String comment = "الطشة معك بتفتح النفس";
    private final int POST_TYPE = 0;
    private final int MEDIA_TYPE = 1;
    private final int COMMENT_TYPE = 2;

    MediaAdapter mediaAdapter;

    public PostDetailsAdapter(Context activity,List<PostDetailsModel> list) {
        context = activity;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case POST_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_text, parent, false);
                return new PostViewHolder(view);
            case MEDIA_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_section, parent, false);
                return new MediaViewHolder(view);
            case COMMENT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_row, parent, false);
                return new CommentsViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        PostDetailsModel object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case POST_TYPE:
                    ((PostViewHolder) holder).profileName.setText("");
                    ((PostViewHolder) holder).postTime.setText(object.getBasePost().getTime());
                    ((PostViewHolder) holder).postDay.setText(object.getBasePost().getDate());
                    ((PostViewHolder) holder).postDesc.setText(object.getBasePost().getTitle());
                    break;
                case MEDIA_TYPE:
                    mediaAdapter = new MediaAdapter(context,object.getBasePost(),object.getImageList());
                    ((MediaViewHolder) holder).mRecyclerView.setAdapter(mediaAdapter);
                    break;
                case COMMENT_TYPE:

                    break;
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            PostDetailsModel object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProfile;
        TextView profileName,postTime,postDay,postDesc;

        public PostViewHolder(View view) {
            super(view);
            imageProfile = view.findViewById(R.id.ivProfile);
            profileName = view.findViewById(R.id.tvTitle);
            postTime = view.findViewById(R.id.tvTime);
            postDay = view.findViewById(R.id.dayTxt);
            postDesc = view.findViewById(R.id.description);

        }
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        public MediaViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.media_recycle);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
    }
    public static class CommentsViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        public CommentsViewHolder(View itemView) {
            super(itemView);

        }
    }

}

