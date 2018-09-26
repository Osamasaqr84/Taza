package com.osamaomar.akhbarak.Activities.Register;

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
import com.osamaomar.akhbarak.Helper.ProgressDialogHelper;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.MailValidationModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register3Activity extends AppCompatActivity {

    EditText mail;
    TextView next,back,name,city;
    String names,citys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        names=getIntent().getStringExtra("name");
        citys=getIntent().getStringExtra("city");
         mail =findViewById(R.id.mail);
        next =findViewById(R.id.next);
        back =findViewById(R.id.back);
        name =findViewById(R.id.name);
        city =findViewById(R.id.city);

        name.setText(names);
        city.setText(citys);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mail.getText().toString().matches(""))
                {
                    mail.setError("أدخل الايميل");
                }
                else if (!isEmailValid(mail.getText().toString()))
                mail.setError("أدخل ايميل الكتروني صحيح");
                else
                    checkmail();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    MailValidationModel mailValidationModel;
    private void checkmail() {
            ProgressDialogHelper.showSimpleProgressDialog(Register3Activity.this, false);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<MailValidationModel> call = apiService.CheckMailValidation(
                    createPartFromString(mail.getText().toString())
            );

            call.enqueue(new Callback<MailValidationModel>() {
                @Override
                public void onResponse(Call<MailValidationModel> call, final Response<MailValidationModel> response) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    if (response.body() != null) {
                        mailValidationModel =response.body();
                        if (mailValidationModel.getMsg().matches("success"))
                        {
                            Intent intent=new Intent(Register3Activity.this,Register4Activity.class);
                            intent.putExtra("name",names);
                            intent.putExtra("city",citys);
                            intent.putExtra("mail",mail.getText().toString());
                            startActivity(intent);
                        }

                        else {
                           // Toast.makeText(Register3Activity.this, getString(R.string.aleadytoken), Toast.LENGTH_SHORT).show();
                            mail.setError(getString(R.string.aleadytoken));
                        }
                    }
                }
                @Override
                public void onFailure(Call<MailValidationModel> call, Throwable t) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    Log.d("fail",call.toString());
                    Toast.makeText(Register3Activity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                }
            });
    }
}
