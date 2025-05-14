package com.example.cinevibes.source.user;

import com.example.cinevibes.repository.user.UserResponseCallback;

import java.util.List;

public abstract class BaseUserAuthenticationRemoteDataSource {
    protected UserResponseCallback userResponseCallback;

    public void setUserResponseCallback(UserResponseCallback userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }

    public abstract void login(String email, String password);
    public abstract void signUp(String nome, String cognome, String email, String password, int eta);

}

