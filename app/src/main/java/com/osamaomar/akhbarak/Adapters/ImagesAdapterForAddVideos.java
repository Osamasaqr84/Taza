package com.osamaomar.akhbarak.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.osamaomar.akhbarak.R;

import java.util.List;


/**
 * Created by hossam on 21/07/2018.
 */

public class ImagesAdapterForAddVideos extends RecyclerView.Adapter<ImagesAdapterForAddVideos.ViewHolder>  {

    private Context ctx;
    private int pos;
    private LayoutInflater inflater;
    private ImageView ivGallery;
    List<Bitmap> mArrayUri;
    public ImagesAdapterForAddVideos(Context ctx, List<Bitmap> mArrayUri) {
        this.ctx = ctx;
        this.mArrayUri = mArrayUri;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gv_item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ivGallery.setImageBitmap(mArrayUri.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayUri.remove(mArrayUri.get(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mArrayUri.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayUri.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

      //  public BloodModel.GetData mItem;

        public ImageView ivGallery,delete;
        public ViewHolder(View view) {

            super(view);
            mView = view;
            ivGallery =  itemView.findViewById(R.id.ivGallery);
            delete =  itemView.findViewById(R.id.delete);


        }
    }

}

