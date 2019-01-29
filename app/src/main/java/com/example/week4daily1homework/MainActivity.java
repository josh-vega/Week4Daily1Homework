package com.example.week4daily1homework;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week4daily1homework.events.UserEvent;
import com.example.week4daily1homework.model.datasource.retrofit.RetrofitHelper;
import com.example.week4daily1homework.model.user.UserResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvLogin, tvId, tvUrl, tvCompany;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin = findViewById(R.id.tvLogin);
        tvId = findViewById(R.id.tvId);
        tvUrl = findViewById(R.id.tvUrl);
        tvCompany = findViewById(R.id.tvCompany);
        imageView = findViewById(R.id.ivPic);
        final Context context = getApplicationContext();

        //retrofit async
        Call<UserResponse> responseCall = RetrofitHelper.getUsers("josh-vega");

            responseCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {


                    String urlUsed = call.request().url().toString(); //get url of requested call
                    String pic = response.body().getItems().get(0).getAvatarUrl();
                    Glide.with(context).load(pic).into(imageView);
                    tvLogin.setText("Login: " + response.body().getItems().get(0).getLogin());
                    tvId.setText("Id: " + response.body().getItems().get(0).getId());
                    tvUrl.setText("Url: " + response.body().getItems().get(0).getUrl());
                    tvCompany.setText("Company Url:" + response.body().getItems().get(0).getOrganizationsUrl());
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("TAG", "onFailure: REQUEST FAILED");
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEvent(UserEvent event){
        if(event != null){
            UserResponse userResponse = event.getUserResponse();
            //Glide.with(this).load(userResponse.getItems().get(0).getAvatarUrl()).into(imageView);
            //tvName.setText("Name: " + userResponse.getName());
            //tvLogin.setText("Login: " +userResponse.getLogin());
            //tvCompany.setText("Company: " + userResponse.getCompany());
            //tvLocation.setText("Location: " + userResponse.getLocation());
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
