package com.osamaomar.akhbarak.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.osamaomar.akhbarak.Activities.LoginActivity;
import com.osamaomar.akhbarak.Activities.PostDetailsActivity;
import com.osamaomar.akhbarak.Assymetric.AsymmetricRecyclerView;
import com.osamaomar.akhbarak.Assymetric.AsymmetricRecyclerViewAdapter;
import com.osamaomar.akhbarak.Assymetric.Utils;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.SpacesItemDecoration;
import com.osamaomar.akhbarak.model.GetPostsModel;
import com.osamaomar.akhbarak.model.ItemImage;
import com.osamaomar.akhbarak.model.PostDetailsModel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {
 
    private List<ItemImage> mItemList = new ArrayList<>();
    private List<GetPostsModel.DataBean> mList;
    private List<PostDetailsModel> intentList= new ArrayList<>();
    private Context mContext;
    private int mDisplay= 3;
    private int mTotal= 0;
    private int lastPosition = -1;
TextView likesCounts;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle,description,time,dateTxt,tvPlace,inTxt,gotologin;
        public AsymmetricRecyclerView recyclerView;
        ImageView userImage,img1,img2,img_vid,likeicon;
        LinearLayout like,comment,share;
        TextView liketext;
        public MyViewHolder(View view) {
            super(view);
            userImage = view.findViewById(R.id.ivProfile);
            tvTitle =  view.findViewById(R.id.tvTitle);
            time = view.findViewById(R.id.tvTime);
            dateTxt = view.findViewById(R.id.dayTxt);
            tvPlace = view.findViewById(R.id.tvPlace);
            inTxt = view.findViewById(R.id.inTxt);
            description = view.findViewById(R.id.description);
            img1 = view.findViewById(R.id.image1);
            img2 = view.findViewById(R.id.image2);
            img_vid = view.findViewById(R.id.vid_play);
            liketext = view.findViewById(R.id.liketext);
            likeicon = view.findViewById(R.id.likeicon);
            likesCounts = view.findViewById(R.id.likesCounts);
            recyclerView =  view.findViewById(R.id.recyclerView);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            recyclerView.setLayoutManager(mLayoutManager);

            like =view.findViewById(R.id.likelayout);
            gotologin =view.findViewById(R.id.gotologin);
          //  gotologin.setMovementMethod(LinkMovementMethod.getInstance());
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                    liketext.setTextColor(mContext.getResources().getColor(R.color.likecolor));
                    int count = Integer.parseInt(likesCounts.getText().toString())+1;
                    likesCounts.setText(count+"");
                }
            });

            recyclerView.setRequestedColumnCount(3);
            recyclerView.setDebugging(true);
            recyclerView.setRequestedHorizontalSpacing(Utils.dpToPx(mContext, 3));
            recyclerView.addItemDecoration(
                    new SpacesItemDecoration(mContext.getResources().getDimensionPixelSize(R.dimen.recycler_padding)));
        }
    }
 
 
    public ParentAdapter(Context context, List<GetPostsModel.DataBean> mList) {
        mContext = context;
        Collections.reverse(mList);
        this.mList = mList;
    }
 
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parent_item, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @SuppressLint({"SimpleDateFormat", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GetPostsModel.DataBean item = mList.get(position);
        mItemList.clear();
        holder.tvTitle.setText(mList.get(position).getUserData().getUser_Name());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.userone);
        requestOptions.error(R.drawable.userone);
        if (!mList.get(position).getUserData().getUser_Photo().matches(""))
        Glide.with(mContext)

                .load(mList.get(position).getUserData().getUser_Photo()).into(holder.userImage);

        mTotal = item.getPhotos().size();
        try {
            holder.time.setText(reFormatTime(item.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (item.getPlace_name() != null){
            holder.inTxt.setVisibility(View.VISIBLE);
            holder.tvPlace.setVisibility(View.VISIBLE);
            holder.tvPlace.setText(item.getPlace_name());
        }
        try {
            holder.dateTxt.setText(formatToYesterdayOrToday(item.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (item.getVideo_Url() != null && item.getPhotos().size() >=2){
            mItemList = prepareImages(item.getPhotos(),item.getPhoto_Url());
            ChildAdapter adapter = new ChildAdapter(mContext,mItemList,mDisplay,mTotal);
            holder.recyclerView.setAdapter(new AsymmetricRecyclerViewAdapter<>(mContext,holder.recyclerView, adapter));
        }else if (item.getVideo_Url()== null && item.getPhotos().size() >=3){
            mItemList = prepareImages(item.getPhotos(),null);
            ChildAdapter adapter = new ChildAdapter(mContext,mItemList,mDisplay,mTotal);
            holder.recyclerView.setAdapter(new AsymmetricRecyclerViewAdapter<>(mContext,holder.recyclerView, adapter));
        }else if(item.getPhotos().size() == 2 && item.getVideo_Url() == null){
            mItemList = prepareImages(item.getPhotos(),null);
            holder.img1.setVisibility(View.VISIBLE);
            holder.img2.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getPhotos().get(0).getPhoto_url()).into(holder.img1);
            Glide.with(mContext).load(item.getPhotos().get(1).getPhoto_url()).into(holder.img2);
        }else if(item.getPhotos().size() == 1 && item.getVideo_Url() != null){
            mItemList = prepareImages(item.getPhotos(),item.getPhoto_Url());
            holder.img1.setVisibility(View.VISIBLE);
            holder.img2.setVisibility(View.VISIBLE);
            holder.img_vid.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getPhoto_Url()).into(holder.img1);
            Glide.with(mContext).load(item.getPhotos().get(0).getPhoto_url()).into(holder.img2);
        }else if(item.getPhotos().size() == 1 && item.getVideo_Url() == null){
            mItemList = prepareImages(item.getPhotos(),null);
            holder.img1.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getPhotos().get(0).getPhoto_url()).into(holder.img1);
        }else if(item.getPhotos().size() == 0 && item.getVideo_Url() != null){
            mItemList = prepareImages(null,item.getPhoto_Url());
            holder.img1.setVisibility(View.VISIBLE);
            holder.img_vid.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getPhoto_Url()).into(holder.img1);
        }else if(item.getPhotos().size() == 0 && item.getVideo_Url() == null){
            mItemList.clear();

        }

        addReadMore(item.getTitle(),holder.description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                intentList.clear();
                intentList.add(new PostDetailsModel(item,null,null,PostDetailsModel.TEXT_TYPE));
                intentList.add(new PostDetailsModel(item,item.getPhotos(),null,PostDetailsModel.MEDIA_TYPE));
                intentList.add(new PostDetailsModel(null,null,null,PostDetailsModel.COMMENT_TYPE));
                Intent intent = new Intent(mContext, PostDetailsActivity.class);
                intent.putExtra("MyDataList", (Serializable) intentList);
                mContext.startActivity(intent);
            }
        });

        setFadeAnimation(holder.itemView);
        //setAnimation(holder.itemView,position);
    }
 
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<ItemImage> prepareImages(List<GetPostsModel.DataBean.PhotosBean> images,String videoThumb){
        int currentOffset = 0;

        List<ItemImage> images1 = new ArrayList<>();
        List<ItemImage> mPathitems= new ArrayList<>();
        boolean isCol2Avail = false;
        images1.clear();
        if (videoThumb != null) {
            images1.add(0, new ItemImage(1, videoThumb, videoThumb));
        }
        if (images != null) {
            for (int i = 0; i < images.size(); i++) {
                ItemImage i2 = new ItemImage(2, images.get(i).getPhoto_url(), images.get(i).getPhoto_url());
                int colSpan2 = Math.random() < 0.2f ? 2 : 1;
                if (colSpan2 == 2 && !isCol2Avail)
                    isCol2Avail = true;
                else if (colSpan2 == 2 && isCol2Avail)
                    colSpan2 = 1;
                int rowSpan2 = colSpan2;
                i2.setColumnSpan(colSpan2);
                i2.setRowSpan(rowSpan2);
                i2.setPosition(currentOffset + 1);
                images1.add(i2);
            }
        }
        if (images!=null)
        { if (images.size()>3) {
            for (int i = 0; i < mDisplay; i++) {
                mPathitems.add(images1.get(i));
            }
            return mPathitems;
        }
        }
        return images1;
    }

    private void addReadMore(final String text, final TextView textView) {

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                addReadLess(text, textView);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //   ds.setColor(getResources().getColor(R.color.color_primary, getTheme()));
                } else {
                    // ds.setColor(getResources().getColor(R.color.color_primary));
                }
            }
        };
        SpannableString ss = null;
        if (text.length()> 55) {
            ss = new SpannableString(text.substring(0, 50) + "  ... " + mContext.getResources().getString(R.string.readmore));
            ss.setSpan(clickableSpan, ss.length() - 12, ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else {
            ss = new SpannableString(text);
        }
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    public String reFormatTime(String time) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat Time = new SimpleDateFormat("hh:mm:ss a", Locale.US);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat Time1 = new SimpleDateFormat("HH:mm",Locale.US);

        Date date = Time.parse(time);
        String myFormat = Time1.format(date);
        return myFormat;
    }

    public static String formatToYesterdayOrToday(String date) throws ParseException {
        Date dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            return "اليوم";
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
            return "أمس";
        } else {
            return date;
        }
    }

    private void addReadLess(final String text, final TextView textView) {
        SpannableString ss = new SpannableString(text +" "+mContext.getResources().getString(R.string.readless));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                addReadMore(text, textView);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //   ds.setColor(getResources().getColor(R.color.color_primary, getTheme()));
                } else {
                    //  ds.setColor(getResources().getColor(R.color.color_primary));
                }
            }
        };
        ss.setSpan(clickableSpan, ss.length() - 10, ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void setAnimation(View viewToAnimate,int position){
        if (position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext,android.R.anim.slide_in_left);
            viewToAnimate.setAnimation(animation);
            lastPosition = position;
        }

    }
    private void setFadeAnimation(View viewToAnimate){

        AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(2000);
        viewToAnimate.startAnimation(animation);
    }
}