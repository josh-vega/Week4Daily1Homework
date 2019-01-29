package com.example.week4daily1homework.model.datasource.retrofit;

import com.example.week4daily1homework.model.repo.RepoResponse;
import com.example.week4daily1homework.model.user.UserResponse;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.week4daily1homework.model.Constants.BASE_URL;
import static com.example.week4daily1homework.model.Constants.PATH;
import static com.example.week4daily1homework.model.Constants.QUERY_USER;


public class RetrofitHelper {
    public static Retrofit createRetrofitInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit createRetrofitForRx(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static Call<UserResponse> getUsers(String username){
        Retrofit retrofit = createRetrofitInstance();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getUserList(username);
    }

    public static Observable<UserResponse> getUserOb() {
        Retrofit retrofit = createRetrofitForRx();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getUserObservable("10");
    }

    public static Call<RepoResponse> getRepos(String username){
        Retrofit retrofit = createRetrofitInstance();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getRepoList(username);
    }
    public interface RemoteService{
        @GET("/search/users")
        Call<UserResponse> getUserList(@Query(QUERY_USER) String username);

        @GET("/search/repositories")
        Call<RepoResponse> getRepoList(@Query(QUERY_USER) String username);

        @GET(PATH)
        Observable<UserResponse> getUserObservable(@Query(QUERY_USER) String username);
    }
}
