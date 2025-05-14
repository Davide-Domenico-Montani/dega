package com.example.cinevibes.repository.user;

import androidx.lifecycle.MutableLiveData;

import com.example.cinevibes.model.Result;

import java.util.List;

public interface IUserRepository {

    MutableLiveData<Result> loginUser(String email, String password);
    void login(String email, String password);

    MutableLiveData<Result> registerUser(String nome, String cognome, String email, String password, int eta);
    void signUp(String nome, String cognome, String email, String password, int eta);


}
