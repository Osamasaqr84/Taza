package com.osamaomar.akhbarak.Activities.Register;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import com.osamaomar.akhbarak.API.ApiClient;
import com.osamaomar.akhbarak.API.ApiInterface;
import com.osamaomar.akhbarak.Activities.CreatePostActivity;
import com.osamaomar.akhbarak.Activities.MainActivity;
import com.osamaomar.akhbarak.Helper.FileUtils;
import com.osamaomar.akhbarak.Helper.PreferenceHelper;
import com.osamaomar.akhbarak.Helper.ProgressDialogHelper;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.RegisterModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register6Activity extends AppCompatActivity {

    TextView next,back,name,city,mail,password,repassword;
    ImageView upload;
    Uri uri;
    String token,photo;
     String pass;
    PreferenceHelper preferenceHelper;
    MultipartBody.Part photo_part ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register6);
        preferenceHelper = new PreferenceHelper(Register6Activity.this);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                 token = instanceIdResult.getToken();
                Log.v("TokenNotification", token);
            }
        });

        final String names=getIntent().getStringExtra("name");
        final String citys=getIntent().getStringExtra("city");
        final String mails=getIntent().getStringExtra("mail");
         pass=getIntent().getStringExtra("repass");

        mail =findViewById(R.id.mail);
        next =findViewById(R.id.next);
        back =findViewById(R.id.back);
        name =findViewById(R.id.name);
        city =findViewById(R.id.city);
        password =findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        upload=findViewById(R.id.imgupload);
        name.setText(names);
        city.setText(citys);
        mail.setText(mails);

        StringBuilder repass2= new StringBuilder();
        for (int i=0;i<pass.length();i++)
            repass2.append("*");

        repassword.setText(repass2.toString());
        password.setText(repass2.toString());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    register();
                }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getProfileImage(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(Register6Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            permission();
        } else {

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(Register6Activity.this);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri = result.getUri();
                upload.setImageURI(uri);////set the image after taking it
                photo_part =prepareFilePart("Photo" , uri);
//
//                Bitmap bm = BitmapFactory.decodeFile(uri.getPath());
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
//                byte[] byteArrayImage = baos.toByteArray();
//                photo = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {
        File file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(Register6Activity.this, fileUri);
        }
        RequestBody requestFile;
            requestFile = RequestBody.create(MediaType.parse("image/*"), file );

        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }

    public void permission() {
        final Dialog dialog = new Dialog(Register6Activity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_permission_cam);
        dialog.show();
        Button cancel_log, open;
        ImageView close_dialog;
        open =  dialog.findViewById(R.id.open);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                final Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + Register6Activity.this.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                Register6Activity.this.startActivity(i);
            }
        });

        cancel_log = (Button) dialog.findViewById(R.id.cancel_log);
        cancel_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        close_dialog = (ImageView) dialog.findViewById(R.id.close_dialog);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    private void register() {
        if (true)
        {
            if (photo==null)
                photo="";
            ProgressDialogHelper.showSimpleProgressDialog(Register6Activity.this, false);
            ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
            Call<RegisterModel> call = apiService.UserRegister
                    (
                    createPartFromString(mail.getText().toString().substring(0,mail.getText().toString().indexOf("@"))),
                    createPartFromString(name.getText().toString()),
                    createPartFromString("123456789"),
                    createPartFromString(mail.getText().toString()),
                    createPartFromString("egypt"),
                    createPartFromString(token),
                    createPartFromString(city.getText().toString()),
                    createPartFromString(pass),
                            photo_part
                  );


            call.enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, final Response<RegisterModel> response) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    if (response.body() != null) {
                        Toast.makeText(Register6Activity.this,"تم التسجيل بنجاح ",Toast.LENGTH_SHORT).show();
                        preferenceHelper.setUserId(String.valueOf(response.body().getUserId()));
                        preferenceHelper.setToken(String.valueOf(response.body().getToken()));
                        startActivity(new Intent(Register6Activity.this, MainActivity.class));
                     }
                    else
                    {
                        Toast.makeText(Register6Activity.this, getString(R.string.aleadytoken), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    Log.d("fail",call.toString());
                    Toast.makeText(Register6Activity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();

                }
            });
        }
        else
        {
          //  Toast.makeText(Register6Activity.this, R.string.complete, Toast.LENGTH_SHORT).show();
        }


    }


}
