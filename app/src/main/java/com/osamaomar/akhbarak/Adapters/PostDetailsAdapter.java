package com.osamaomar.akhbarak.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.osamaomar.akhbarak.Activities.CommentsActivity;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.PostDetailsModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PostDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context context;
    private List<PostDetailsModel> mList = new ArrayList<>();
    String name = "Mohamed jaber jabarin";
    String comment = "الطشة معك بتفتح النفس";
    private final int POST_TYPE = 0;
    private final int MEDIA_TYPE = 1;
    private final int COMMENT_TYPE = 2;
    MediaAdapter mediaAdapter;
    private int PICK_IMAGE_MULTIPLE=1;
  //  public ImageButton getimages;
    private ImageView  imgcancel, commentimg;
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
                    ((PostViewHolder) holder).profileName.setText(object.getBasePost().getUserData().getUser_Name());
                    ((PostViewHolder) holder).postTime.setText(object.getBasePost().getTime());
                    ((PostViewHolder) holder).postDay.setText(object.getBasePost().getDate());
                    ((PostViewHolder) holder).postDesc.setText(object.getBasePost().getTitle());
                    break;

                case MEDIA_TYPE:
                    mediaAdapter = new MediaAdapter(context,object.getBasePost(),object.getImageList());
                    ((MediaViewHolder) holder).mRecyclerView.setAdapter(mediaAdapter);
                    break;

                case COMMENT_TYPE:
        ((CommentsViewHolder)holder).comment_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CommentsActivity.class));
            }
        });

                    ((CommentsViewHolder)holder).getimages.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
                            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions((Activity) context,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
                            } else {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                ((Activity)context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
                            }
                        }
                    });

                    break;
            }
        }
    }
    public interface CallbackInterface{
        /**
         * Callback invoked when clicked
         * @param position - the position
         * @param text - the text to pass back
         */
        void onHandleSelection(int position, String text);
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
        if (requestCode == 1)
        {
            // Get the Image from data
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            if(data.getData()!=null){
                Uri mImageUri=data.getData();
                commentimg.setImageURI(mImageUri);
                commentimg.setVisibility(View.VISIBLE);
                imgcancel.setVisibility(View.VISIBLE);

            }
            else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        commentimg.setImageURI(uri);
                        commentimg.setVisibility(View.VISIBLE);
                        imgcancel.setVisibility(View.VISIBLE);
                    }
                    //Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                }
//                if (mArrayUri.size() > 0) {
//                    for (int i = 0; i < mArrayUri.size(); i++) {
//                        parts.add(prepareFilePart("Photos" + "[" + i + "]", mArrayUri.get(i),0));
//                    }
            }

        }
        else if (requestCode == 1234)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            ((Activity)context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
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
    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        private EditText commenttxt;
        ImageButton getimages;
        LinearLayout comment_add;
        public CommentsViewHolder(View itemView) {
            super(itemView);

            comment_add = itemView.findViewById(R.id.comment_add);
            getimages = itemView.findViewById(R.id.getimages);
            commentimg = itemView.findViewById(R.id.commentimg);
            imgcancel = itemView.findViewById(R.id.imgcancel);
        }
    }

}

