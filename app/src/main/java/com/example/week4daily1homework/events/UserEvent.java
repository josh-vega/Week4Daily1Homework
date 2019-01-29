package com.example.week4daily1homework.events;

import com.example.week4daily1homework.model.user.UserResponse;

public class UserEvent {
    private UserResponse userResponse;

    public UserEvent(UserResponse userResponse){
        this.userResponse = userResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
