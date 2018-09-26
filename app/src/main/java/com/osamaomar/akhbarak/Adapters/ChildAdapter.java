package com.osamaomar.akhbarak.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.osamaomar.akhbarak.Assymetric.AGVRecyclerViewAdapter;
import com.osamaomar.akhbarak.Assymetric.AsymmetricItem;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.ItemImage;

import java.util.List;

class ChildAdapter extends AGVRecyclerViewAdapter<ViewHolder> {
    private final List<ItemImage> items;
    Context context;
    private int mDisplay = 0;
    private int mTotal = 0;

    public ChildAdapter(Context mContext, List<ItemImage> items, int mDisplay, int mTotal) {
      this.items = items;
      this.mDisplay = mDisplay;
      this.mTotal = mTotal;
      this.context = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      Log.d("RecyclerViewActivity", "onCreateView");
      return new ViewHolder(parent, viewType,items);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Log.d("RecyclerViewActivity", "onBindView position=" + position);
      holder.bind(context,items,position,mDisplay,mTotal);
    }

    @Override
    public int getItemCount() {
      return items.size();
    }

    @Override
    public AsymmetricItem getItem(int position) {
      return (AsymmetricItem) items.get(position);
    }

    @Override
    public int getItemViewType(int position) {
      return position % 2 == 0 ? 1 : 0;
    }
  }


class ViewHolder extends RecyclerView.ViewHolder {
  private final ImageView mImageView,img_vid;
  private final TextView textView;

  public ViewHolder(ViewGroup parent, int viewType, List<ItemImage> items) {
    super(LayoutInflater.from(parent.getContext()).inflate(
            R.layout.adapter_item, parent, false));

    mImageView = itemView.findViewById(R.id.mImageView);
    img_vid = itemView.findViewById(R.id.vid_play);
    textView = itemView.findViewById(R.id.tvCount);
  }

  @SuppressLint("CheckResult")
  public void bind(final Context context, List<ItemImage> item, int position, int mDisplay, int mTotal) {

      if (item.get(position).getItemImageId() == 1){
          img_vid.setVisibility(View.VISIBLE);
          Glide.with(context).load(item.get(position).getImagePath()).into(mImageView);
      }else {
          Glide.with(context).load(item.get(position).getImagePath()).into(mImageView);
          textView.setText("+" + (mTotal - mDisplay));
          img_vid.setVisibility(View.GONE);
      }
      if(mTotal > mDisplay)
      {
          if(position  == mDisplay-1) {
              textView.setVisibility(View.VISIBLE);
              mImageView.setImageAlpha(72);
          }else{
              textView.setVisibility(View.INVISIBLE);
              mImageView.setImageAlpha(255);
          }
      } else {
          mImageView.setImageAlpha(255);
          textView.setVisibility(View.INVISIBLE);
      }
       // textView.setText(String.valueOf(item.getPosition()));
  }
}
