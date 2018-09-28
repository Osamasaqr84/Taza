package com.osamaomar.akhbarak.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.osamaomar.akhbarak.API.ApiClient;
import com.osamaomar.akhbarak.API.ApiInterface;
import com.osamaomar.akhbarak.Adapters.ImagesAdapterForAddImages;
import com.osamaomar.akhbarak.Adapters.ParentAdapter;
import com.osamaomar.akhbarak.Helper.CheckLanguage;
import com.osamaomar.akhbarak.Helper.PreferenceHelper;
import com.osamaomar.akhbarak.Helper.ProgressDialogHelper;
import com.osamaomar.akhbarak.R;
import com.osamaomar.akhbarak.model.GetPostsModel;
import com.osamaomar.akhbarak.model.ItemImage;
import com.osamaomar.akhbarak.model.ItemList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    int mTotal_Size = 0;
    private GridView gvGallery;
    RecyclerView recyclerViewGallery;
    CardView cardView;
    private ImagesAdapterForAddImages galleryAdapter2;
    ArrayList<ItemImage> Pathitems = new ArrayList<>();
    private List<ItemList> mItemList = new ArrayList<>();
    private List<GetPostsModel.DataBean> mLists = new ArrayList<>(10);
    private RecyclerView recyclerView;
    private ParentAdapter mAdapter;
    PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CheckLanguage(this);
        setContentView(R.layout.activity_main);
        getPostsData();
        cardView = findViewById(R.id.card_view);
        recyclerView =  findViewById(R.id.recycler_view);
        gvGallery = findViewById(R.id.gv);
        recyclerViewGallery = findViewById(R.id.images_recycler);
        mTotal_Size = Pathitems.size();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        preferenceHelper = new PreferenceHelper(MainActivity.this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0 && cardView.getVisibility() == View.GONE){
                    cardView.setVisibility(View.VISIBLE);
                    cardView.setAlpha(0.0f);
                    cardView.animate()
                            .translationY(1)
                            .alpha(1.0f)
                            .setListener(null);
                }else if (dy > 0 && cardView.getVisibility() == View.VISIBLE){
                    cardView.animate()
                            .translationY(0)
                            .alpha(0.0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    cardView.setVisibility(View.GONE);
                                }
                            });
                }
            }
        });

    }
    ProgressDialogHelper progressDialogHelper = new ProgressDialogHelper();

    private void getPostsData() {

        progressDialogHelper.showSimpleProgressDialog(MainActivity.this,false);
        ApiInterface apiService =  ApiClient.getClient().create(ApiInterface.class);
        Call<GetPostsModel> call = apiService.getPostsData("70");
        call.enqueue(new Callback<GetPostsModel>() {
            @Override
            public void onResponse(@NonNull Call<GetPostsModel> call, @NonNull Response<GetPostsModel> response) {
                if (response.body() != null){
                    for(GetPostsModel.DataBean getPostsModel:response.body().getData()){
                        mLists.add(getPostsModel);
                    }
                }
                mAdapter = new ParentAdapter(MainActivity.this,mLists);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                progressDialogHelper.removeSimpleProgressDialog();

            }

            @Override
            public void onFailure(Call<GetPostsModel> call, Throwable t) {
                progressDialogHelper.removeSimpleProgressDialog();

            }
        });

    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }


    public void opensetting(View view) {
        startActivity(new Intent(MainActivity.this, SettingActivity.class));
    }

    public void openhashtage(View view) {
        startActivity(new Intent(MainActivity.this, HashtagsActivity.class));

    }

    public void opensearch(View view) {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));

    }

    public void addPost(View view) {

        if (preferenceHelper.getUserId()!=null)
        startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
        else
            Toast.makeText(MainActivity.this,"يجب تسجيل الدخول لامكانية اضافة خبر",Toast.LENGTH_SHORT).show();
    }
}
