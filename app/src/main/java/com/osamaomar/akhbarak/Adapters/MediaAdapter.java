package com.osamaomar.akhbarak.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.osamaomar.akhbarak.Activities.ImageDetails;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.GetPostsModel;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.mViewHolder> {
    private Context mContext;
    private GetPostsModel.DataBean mPostDataBean;
    private List<GetPostsModel.DataBean.PhotosBean> mList;
    private boolean status = true;

    public MediaAdapter(Context context, GetPostsModel.DataBean basePost, List<GetPostsModel.DataBean.PhotosBean> imageList) {
        this.mContext = context;
        this.mPostDataBean = basePost;
        this.mList = imageList;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_item, parent, false);

        return new mViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, final int position) {
        if (mPostDataBean.getVideo_Url() != null && status){
            holder.ic_imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mPostDataBean.getPhoto_Url()).into(holder.imageView);
            status = false;
        }else if (status){
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mList.get(position).getPhoto_url()).into(holder.imageView);
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(mList.get(position-1).getPhoto_url()).into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0 && mPostDataBean.getVideo_Url() != null){
                    try {
                        Intent in = new Intent(Intent.ACTION_VIEW);
                        in.setDataAndType(Uri.parse(String.valueOf(mPostDataBean.getVideo_Url())), "video/*");
                        mContext.startActivity(in);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }else if (position >= 1 && mPostDataBean.getVideo_Url() != null){
                    Intent intent = new Intent(mContext, ImageDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("imageUrl",mList.get(position-1).getPhoto_url());
                    mContext.startActivity(intent);
                }else if (mPostDataBean.getVideo_Url() == null){
                    Intent intent = new Intent(mContext, ImageDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("imageUrl",mList.get(position).getPhoto_url());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPostDataBean.getVideo_Url() != null)
            return mList.size() + 1;
        else
            return mList.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,ic_imageView;
        public mViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_post);
            ic_imageView = itemView.findViewById(R.id.vid_play);
        }

    }
}
