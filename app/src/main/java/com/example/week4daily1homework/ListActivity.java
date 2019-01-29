package com.example.week4daily1homework;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.week4daily1homework.model.datasource.retrofit.RetrofitHelper;
import com.example.week4daily1homework.model.repo.Item;
import com.example.week4daily1homework.model.repo.RepoResponse;
import com.example.week4daily1homework.model.user.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Context context = getApplicationContext();
        recyclerView = findViewById(R.id.rvMain);

        //retrofit async
        Call<RepoResponse> responseCall = RetrofitHelper.getRepos("josh-vega");

        responseCall.enqueue(new Callback<RepoResponse>() {
            @Override
            public void onResponse(Call<RepoResponse> call, Response<RepoResponse> response) {


                String urlUsed = call.request().url().toString(); //get url of requested call
                ArrayList<Item> resultArrayList = new ArrayList<>(response.body().getItems());
                rvAdapter = new RecyclerViewAdapter(resultArrayList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(rvAdapter);

            }

            @Override
            public void onFailure(Call<RepoResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: REQUEST FAILED");
            }
        });

    }
}
