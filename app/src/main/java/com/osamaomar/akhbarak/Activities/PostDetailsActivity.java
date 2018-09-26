package com.osamaomar.akhbarak.Activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.osamaomar.akhbarak.Adapters.PostDetailsAdapter;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.PostDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class PostDetailsActivity extends AppCompatActivity {

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

}
