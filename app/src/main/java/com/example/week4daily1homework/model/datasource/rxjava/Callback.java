package com.example.week4daily1homework.model.datasource.rxjava;

import com.example.week4daily1homework.model.user.UserResponse;

public interface Callback {
    void onSuccess(UserResponse userResponse);
}
