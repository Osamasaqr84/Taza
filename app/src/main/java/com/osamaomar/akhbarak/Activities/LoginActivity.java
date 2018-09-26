package com.osamaomar.akhbarak.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.osamaomar.akhbarak.API.ApiClient;
import com.osamaomar.akhbarak.API.ApiInterface;
import com.osamaomar.akhbarak.Activities.Register.Register1Activity;

import com.osamaomar.akhbarak.Helper.PreferenceHelper;
import com.osamaomar.akhbarak.Helper.ProgressDialogHelper;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.LoginModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView gotoregister;
    EditText mail,password;
    PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferenceHelper = new PreferenceHelper(LoginActivity.this);

        mail =findViewById(R.id.uname);
        password =findViewById(R.id.pass);
        gotoregister = findViewById(R.id.gotoregister);
        gotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(new Intent(LoginActivity.this, Register1Activity.class));
            }
        });
    }

    public void login(View view) {

        if (mail.getText().toString().matches(""))
            mail.setError("ادخل الايميل ");
        else if (!isEmailValid(mail.getText().toString()))
            mail.setError("ادخل اميل الكتروني صحيح ");
        else if (password.getText().toString().matches(""))
            password.setError("ادخل كلمة المرور");

        else
            login();

    }

LoginModel loginModel;
    private void login() {
        ProgressDialogHelper.showSimpleProgressDialog(LoginActivity.this, false);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<LoginModel> call = apiService.UserLogin(
                    createPartFromString(mail.getText().toString().trim()),
                    createPartFromString(password.getText().toString().trim()));
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, final Response<LoginModel> response) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    loginModel = response.body();

                    if (response.body() != null) {
                        Log.d("response" , response.body().toString()) ;

                        if (loginModel.getMsg().matches("success"))
                        {
                            preferenceHelper.setUserId(String.valueOf(response.body().getUserId()));
                            preferenceHelper.setToken(String.valueOf(response.body().getToken()));
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }

                       else
                           Toast.makeText(LoginActivity.this,getResources().getString(R.string.userorpasserror),Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                       // Toast.makeText(LoginActivity.this, getString(R.string.userorpasserror), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    Log.d("fail",call.toString());
                    Toast.makeText(LoginActivity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();

                }
            });

     /*   else
        {

           // Toast.makeText(LoginActivity.this, R.string.complete, Toast.LENGTH_SHORT).show();
        }*/



    }

    public static boolean isEmailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
}
