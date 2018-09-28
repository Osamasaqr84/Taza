package com.osamaomar.akhbarak.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osamaomar.akhbarak.API.ApiClient;
import com.osamaomar.akhbarak.API.ApiInterface;
import com.osamaomar.akhbarak.Adapters.ImagesAdapterForAddImages;
import com.osamaomar.akhbarak.Adapters.ImagesAdapterForAddVideos;

import com.osamaomar.akhbarak.Helper.BroadcastHelper;
import com.osamaomar.akhbarak.Helper.FileUtils;
import com.osamaomar.akhbarak.Helper.PreferenceHelper;
import com.osamaomar.akhbarak.Helper.ProgressDialogHelper;

import com.osamaomar.akhbarak.R;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {

    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    RecyclerView recyclerViewGallery,recyclerViewvideos;
    TextView locationtxt;
    private ImagesAdapterForAddImages galleryAdapter2;
    private ImagesAdapterForAddVideos videosAdapter;
    int PICK_IMAGE_MULTIPLE = 1,REQUEST_TAKE_GALLERY_VIDEO = 2;
    List<MultipartBody.Part> parts = new ArrayList<>();
    List<MultipartBody.Part> Videosparts = new ArrayList<>();
    MultipartBody.Part firstVideospart ;
    String  start_lat="";
    String  start_log="";
    String  cityname="";
    String encoded ="";
    EditText tvTitle;
    PreferenceHelper preferenceHelper;
    private static final int REQUEST_CODE = 123;
    private ArrayList<String> mResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle =findViewById(R.id.tvTitle);
        recyclerViewGallery = findViewById(R.id.images_recycler);
        recyclerViewvideos = findViewById(R.id.videos_recycler);
        locationtxt= findViewById(R.id.location);
        preferenceHelper =new PreferenceHelper(CreatePostActivity.this);
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }

    private void addPost(String userId, String title, List<MultipartBody.Part> partList,
                           String cityname, String lat, String lang) {

        ProgressDialogHelper.showSimpleProgressDialog(CreatePostActivity.this, false);
        ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = null;
        if (Videosparts.size()>0)
         call = apiService.ADDPost(
                partList,
                 firstVideospart,
                 Videosparts.get(0),
                createPartFromString(userId),
                createPartFromString(title),
                createPartFromString(lat),
                createPartFromString(lang),
                createPartFromString(cityname),
                createPartFromString("")
        );

        else
            call = apiService.ADDPost2(
                    partList,
                    createPartFromString(""),
                    createPartFromString(""),
                    createPartFromString(userId),
                    createPartFromString(title),
                    createPartFromString(lat),
                           createPartFromString(lang),
                    createPartFromString(cityname),
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
                        Toast.makeText(CreatePostActivity.this,getResources().getString(R.string.addsuccess),Toast.LENGTH_LONG).show();
                       CreatePostActivity.this.finish();
                      //  startActivity(new Intent(CreatePostActivity.this, ExpermentsActivity.class));
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


    public void getImages(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(CreatePostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
        } else {
            // start multiple photos selector
            Intent intent = new Intent(CreatePostActivity.this, ImagesSelectorActivity.class);
            // max number of images to be selected
                        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 5);
            // min size of image which will be shown; to filter tiny images (mainly icons)
                        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
            // show camera or not
                        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, false);
            // pass current selected images as the initial value
                        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
            // start the selector
                        startActivityForResult(intent, REQUEST_CODE);


//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  try {
            // When an Image is picked

            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK
                    && null != data) {


                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;

                // show results in textview
                StringBuffer sb = new StringBuffer();
                sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
                for(String result : mResults) {
                    mArrayUri.add(Uri.fromFile(new File(result)));
                }

                galleryAdapter2 = new ImagesAdapterForAddImages(getApplicationContext(),mArrayUri);
                           recyclerViewGallery.setAdapter(galleryAdapter2);
                if (mArrayUri.size() > 0) {
                        for (int i = 0; i < mArrayUri.size(); i++) {
                             parts.add(prepareFilePart("Photos" + "[" + i + "]", mArrayUri.get(i),0));
                        }
                    }


               // tvResults.setText(sb.toString());
//                mArrayUri.clear();
//                // Get the Image from data
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                if(data.getData()!=null){
//                    Uri mImageUri=data.getData();
//                    Cursor cursor = getContentResolver().query(mImageUri,
//                            filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//                    cursor.close();
//                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
//                    mArrayUri.add(mImageUri);
//                    galleryAdapter2 = new ImagesAdapterForAddImages(getApplicationContext(),mArrayUri);
//                    recyclerViewGallery.setAdapter(galleryAdapter2);
//                    if (mArrayUri.size() > 0) {
//                        for (int i = 0; i < mArrayUri.size(); i++) {
//                           parts.add(prepareFilePart("Photos" + "[" + i + "]", mArrayUri.get(i),0));
//                            Log.i("[5]", "[" + i + "]");
//                        }
//                    }
//                } else {
//                    if (data.getClipData() != null) {
//                        ClipData mClipData = data.getClipData();
//                        for (int i = 0; i < mClipData.getItemCount(); i++) {
//                            ClipData.Item item = mClipData.getItemAt(i);
//                            Uri uri = item.getUri();
//                            mArrayUri.add(uri);
//                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
//                            // Move to first row
//                            cursor.moveToFirst();
//                            cursor.close();
//                            galleryAdapter2 = new ImagesAdapterForAddImages(getApplicationContext(),mArrayUri);
//                            recyclerViewGallery.setAdapter(galleryAdapter2);
//                        }
//                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
//                    }
//                    if (mArrayUri.size() > 0) {
//                        for (int i = 0; i < mArrayUri.size(); i++) {
//                             parts.add(prepareFilePart("Photos" + "[" + i + "]", mArrayUri.get(i),0));
//                        }
//                    }
//                }
            }
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {

            }
        }
            else if (requestCode == REQUEST_TAKE_GALLERY_VIDEO&& resultCode == RESULT_OK)
            {
                if (data!=null)
                getSelectedVideos(data);
            }

         else {
                Toast.makeText(this, getResources().getString(R.string.notselect),
                        Toast.LENGTH_LONG).show();
            }
//        } catch (Exception e) {
//            Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_LONG)
//                    .show();
//            Log.v("exc",e.getMessage());
//
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void openmap(View view) {
        startActivity(new Intent(CreatePostActivity.this,MapActivity.class));
    }

    public void getVideos(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);
        }

        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
        }
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri, int video_or_image) {
        File file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(CreatePostActivity.this, fileUri);
        }
        RequestBody requestFile;
        if (video_or_image ==0)
         requestFile = RequestBody.create(MediaType.parse("image/*"), file );

        else
             requestFile = RequestBody.create( MediaType.parse("video/*"), file );

        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }

    private Uri getImageUri(Context context, Bitmap inImage) {
        String path="";
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
             path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        }
        catch (Exception e)
        {
            Log.d("ds",e.getMessage());
        }
        return Uri.parse(path);
    }


    private List<String> getSelectedVideos(Intent data) {

        List<String> result = new ArrayList<>();
        List<Bitmap> images = new ArrayList<>();
        List<Uri> mArrayUriVideos = new ArrayList<>();
        ClipData clipData = data.getClipData();
        if(clipData != null) {
            for(int i=0;i<clipData.getItemCount();i++) {
                ClipData.Item videoItem = clipData.getItemAt(i);
                Uri videoURI = videoItem.getUri();
                String filePath = getPathofvideos(this, videoURI);
                result.add(filePath);
                mArrayUriVideos.add(videoURI);
            }

            if (mArrayUriVideos.size() > 0) {
                for (int i = 0; i < mArrayUriVideos.size(); i++) {
                    Videosparts.add(prepareFilePart("Video" , mArrayUriVideos.get(i),1));
                }
            }

        }

        else {
            Uri videoURI = data.getData();
            String filePath = getPathofvideos(this, videoURI);
            result.add(filePath);
            String  fileExtension = MimeTypeMap.getFileExtensionFromUrl(filePath);
            Bitmap bitmap2 = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
            images.add(bitmap2);

//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//            byte[] byteArray = byteArrayOutputStream .toByteArray();
//             encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Uri uriVideo =  getImageUri(CreatePostActivity.this,bitmap2);
            firstVideospart =prepareFilePart("Photo" , uriVideo,0);

            mArrayUriVideos.add(videoURI);
            if (mArrayUriVideos.size() > 0){
                for (int i = 0; i < mArrayUriVideos.size(); i++) {
                    Videosparts.add(prepareFilePart("Video" , mArrayUriVideos.get(i),1));
                }
            }
        }

        videosAdapter = new ImagesAdapterForAddVideos (getApplicationContext(),images);
        recyclerViewvideos.setAdapter(videosAdapter);

        return result;
    }

    public static String getPathofvideos(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {

            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }



    Receiver receiver;
    boolean isReciverRegistered = false;

    @Override
    public void onResume() {
        super.onResume();
        if (receiver == null) {
            receiver = new Receiver();
            IntentFilter filter = new IntentFilter(BroadcastHelper.ACTION_NAME);
            registerReceiver(receiver, filter);
            isReciverRegistered = true;
        }
    }

    @Override
    public void onDestroy() {
        if (isReciverRegistered) {
            if (receiver != null)
                unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    public void addPostmethod(View view) {

        if (preferenceHelper.getUserId() != null) {

            if (tvTitle.getText().toString().matches(""))
                tvTitle.setError("ادخل وصف الخبر ");
            else {
                addPost(preferenceHelper.getUserId(), tvTitle.getText().toString(), parts,
                        cityname, start_lat, start_log);
            }
        } else
            Toast.makeText(CreatePostActivity.this, "يجب تسجيل الدخول اولا", Toast.LENGTH_SHORT).show();

    }
    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Log.v("r", "receive " + arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME));
            String methodName = arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName != null && methodName.length() > 0) {
                Log.v("receive", methodName);
                switch (methodName) {
                    case "place_details":
                        SharedPreferences search_prefs = CreatePostActivity.this.getSharedPreferences("place_details", Context.MODE_PRIVATE);
                        start_lat = search_prefs.getString("start_lat", null);
                        start_log = search_prefs.getString("start_lng", null);
                        String  address = search_prefs.getString("start_address", null);
                        cityname = search_prefs.getString("cityname", null);
                        locationtxt.setText(" في "+ cityname);

                    default:
                        break;
                }
            }
        }
    }

}
