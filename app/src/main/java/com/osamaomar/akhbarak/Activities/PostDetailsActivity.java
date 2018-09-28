package com.osamaomar.akhbarak.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.osamaomar.akhbarak.Adapters.PostDetailsAdapter;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.PostDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class PostDetailsActivity extends AppCompatActivity implements PostDetailsAdapter.CallbackInterface{

    RecyclerView recyclerView;
    PostDetailsAdapter postDetailsAdapter;
    List<Drawable> mList = new ArrayList<>();
    List<PostDetailsModel> mListmodel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.images);
        mList.add(getResources().getDrawable(R.drawable.q));
        mList.add(getResources().getDrawable(R.drawable.q));
        mList.add(getResources().getDrawable(R.drawable.q));
        mList.add(getResources().getDrawable(R.drawable.q));
        mListmodel = (List<PostDetailsModel>) getIntent().getSerializableExtra("MyDataList");
        postDetailsAdapter = new PostDetailsAdapter(PostDetailsActivity.this,mListmodel);

      //  recyclerView.setLayoutManager(new LinearLayoutManager(PostDetailsActivity.this));
        recyclerView.setAdapter(postDetailsAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onHandleSelection(requestCode,data.getDataString());
        postDetailsAdapter.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onHandleSelection(int position, String text) {
       // onActivityResult(1234,-1,);

    }
}
