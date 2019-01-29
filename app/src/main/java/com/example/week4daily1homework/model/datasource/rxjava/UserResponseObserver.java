package com.example.week4daily1homework.model.datasource.rxjava;

import android.util.Log;

import com.example.week4daily1homework.model.user.UserResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserResponseObserver implements Observer<UserResponse> {
    Callback callback;
    UserResponse userResponse = new UserResponse();

    public UserResponseObserver(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d("TAG", "onSubscribe: SUBSCRIBING");
    }

    @Override
    public void onNext(UserResponse userResponse) {
        this.userResponse = userResponse;
        Log.d("TAG", "onNext: " + userResponse.getItems().get(0).getLogin());
    }

    @Override
    public void onError(Throwable e) {
        Log.d("TAG", "onError: ", e);
    }

    @Override
    public void onComplete() {
        callback.onSuccess(userResponse);
    }
}
