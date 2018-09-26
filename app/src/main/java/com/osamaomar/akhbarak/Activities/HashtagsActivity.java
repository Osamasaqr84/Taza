package com.osamaomar.akhbarak.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.osamaomar.akhbarak.API.ApiClient;
import com.osamaomar.akhbarak.API.ApiInterface;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.TagsModel;

import co.lujun.androidtagview.TagContainerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HashtagsActivity extends AppCompatActivity {

    TagContainerLayout mTagContainerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtags);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTagContainerLayout =  findViewById(R.id.tagcontainerLayout);
         getHashtagesData();
    }

    private TagsModel tagsModel;
    Call<TagsModel> calls;
    private void getHashtagesData() {
        //  progressBar.setVisibility(View.VISIBLE);
        int rowsnum = 10000;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getTagsData(rowsnum);
        calls.enqueue(new Callback<TagsModel>(){
            @Override
            public void onResponse(Call<TagsModel> call, final Response<TagsModel> response) {
                if (response.body() != null) {
                    for (int i= 0;i<response.body().getData().size();i++)
                        mTagContainerLayout.addTag(response.body().getData().get(i).getName());
                }
            }
            @Override
            public void onFailure(Call<TagsModel> call, Throwable t) {
                    Toast.makeText(HashtagsActivity.this, getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
