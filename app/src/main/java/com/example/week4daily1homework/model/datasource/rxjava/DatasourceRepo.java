package com.example.week4daily1homework.model.datasource.rxjava;

import com.example.week4daily1homework.model.datasource.retrofit.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DatasourceRepo {
    RetrofitHelper retrofitHelper = new RetrofitHelper();
    public void getUserResponse(/*String gender,*/ final Callback callback){
        retrofitHelper.getUserOb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserResponseObserver(callback));
    }
}
