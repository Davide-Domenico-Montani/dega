package com.example.cinevibes.source.user;

import com.example.cinevibes.model.User;
import com.example.cinevibes.repository.user.UserResponseCallback;

public abstract class BaseUserDataRemoteDataSource {
    protected UserResponseCallback userResponseCallback;

    public void setUserResponseCallback(UserResponseCallback userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }

    public abstract void saveUserData(User user);
}
