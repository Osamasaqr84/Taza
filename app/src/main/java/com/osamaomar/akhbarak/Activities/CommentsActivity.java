package com.osamaomar.akhbarak.Activities;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.osamaomar.akhbarak.API.ApiClient;
import com.osamaomar.akhbarak.API.ApiInterface;
import com.osamaomar.akhbarak.Adapters.CommentsAdapter;
import com.osamaomar.akhbarak.Adapters.ImagesAdapterForAddImages;
import com.osamaomar.akhbarak.Adapters.ParentAdapter;
import com.osamaomar.akhbarak.Helper.PreferenceHelper;
import com.osamaomar.akhbarak.Helper.ProgressDialogHelper;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.CommentsModel;
import com.osamaomar.akhbarak.model.GetPostsModel;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

import static android.content.Intent.ACTION_GET_CONTENT;

public class CommentsActivity extends AppCompatActivity {

    ImageView commentimg,imgcancel;
    int PICK_IMAGE_MULTIPLE=1;
    PreferenceHelper preferenceHelper;
    EditText commenttext;
    int postid;
    MultipartBody.Part imgpart;
    CommentsAdapter commentsAdapter;
    RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        commentimg= findViewById(R.id.commentimg);
        imgcancel= findViewById(R.id.imgcancel);
        commenttext= findViewById(R.id.commenttext);
        preferenceHelper = new PreferenceHelper(CommentsActivity.this);
        postid = getIntent().getIntExtra("postid",91);
        recyclerView = findViewById(R.id.recycler_view_comments);
        getPostsData();
    }

    private void getPostsData() {
       ProgressDialogHelper.showSimpleProgressDialog(CommentsActivity.this,false);
        ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
        Call<CommentsModel> call = apiService.getCommentsData(String.valueOf(postid));
        call.enqueue(new Callback<CommentsModel>() {
            @Override
            public void onResponse(@NonNull Call<CommentsModel> call, @NonNull Response<CommentsModel> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null){
                    commentsAdapter = new CommentsAdapter(CommentsActivity.this,response.body().getData());
                    recyclerView.setAdapter(commentsAdapter);
                    recyclerView.scrollToPosition(response.body().getData().size());
                }
                }
            @Override
            public void onFailure(Call<CommentsModel> call, Throwable t) {
               ProgressDialogHelper.removeSimpleProgressDialog();
            }
        });
    }

    public void sendcomment(View view) {
        if (preferenceHelper.getUserId()==null) {
            Toast.makeText(CommentsActivity.this, "يجب تسجيل الدخول لاضافة تعليق ", Toast.LENGTH_SHORT).show();
            return;
        }

         if (commenttext.getText().toString().matches("")) {
             commenttext.setError("اكتب تعليقك");
                return;
        }
        addComment(preferenceHelper.getUserId(),commenttext.getText().toString(),String.valueOf(postid),imgpart);
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }
    private void addComment(String userId, String comment,String postId, MultipartBody.Part partList)
    {
        ProgressDialogHelper.showSimpleProgressDialog(CommentsActivity.this, false);
        ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = null;
        if (partList!=null)
            call = apiService.ADDComment(
                    createPartFromString(userId),
                    createPartFromString(postId),
                    createPartFromString(comment),
                    partList
            );
        else
            call = apiService.ADDComment2(
                    createPartFromString(userId),
                    createPartFromString(postId),
                    createPartFromString(comment),
                    createPartFromString("")
            );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {
                    if (response.isSuccessful())
                    {
                        Log.d("success","ss");
                        Toast.makeText(CommentsActivity.this,getResources().getString(R.string.addsuccess),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                Log.d("fail",call.toString());
                // Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void getImage(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(CommentsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            // Get the Image from data
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            if(data.getData()!=null){
                Uri mImageUri=data.getData();
                commentimg.setImageURI(mImageUri);

                Cursor cursor = getContentResolver().query(mImageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                cursor.close();
                ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                mArrayUri.add(mImageUri);
                commentimg.setVisibility(View.VISIBLE);
                imgcancel.setVisibility(View.VISIBLE);
              //  galleryAdapter2 = new ImagesAdapterForAddImages(getApplicationContext(),mArrayUri);
                //recyclerViewGallery.setAdapter(galleryAdapter2);
                if (mArrayUri.size() > 0) {
                    for (int i = 0; i < mArrayUri.size(); i++) {
                      //  parts.add(prepareFilePart("Photos" + "[" + i + "]", mArrayUri.get(i),0));
                        Log.i("[5]", "[" + i + "]");
                    }
                }
              }
              else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                       // mArrayUri.add(uri);
                        commentimg.setImageURI(uri);
                        commentimg.setVisibility(View.VISIBLE);
                        imgcancel.setVisibility(View.VISIBLE);
                        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();
                        cursor.close();
                      //  galleryAdapter2 = new ImagesAdapterForAddImages(getApplicationContext(),mArrayUri);
                        //recyclerViewGallery.setAdapter(galleryAdapter2);
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
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
          //  startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
        }
    }

    public void deleteimg(View view) {
        if (commentimg.getVisibility()==View.VISIBLE)
            commentimg.setVisibility(View.GONE);
        if (imgcancel.getVisibility()==View.VISIBLE)
            imgcancel.setVisibility(View.GONE);
    }


}
