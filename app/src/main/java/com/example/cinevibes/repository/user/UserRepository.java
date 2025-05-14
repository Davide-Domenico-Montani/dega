package com.example.cinevibes.repository.user;

import androidx.lifecycle.MutableLiveData;

import com.example.cinevibes.model.Result;
import com.example.cinevibes.model.User;
import com.example.cinevibes.source.user.BaseUserAuthenticationRemoteDataSource;
import com.example.cinevibes.source.user.BaseUserDataRemoteDataSource;

import java.util.List;

public class UserRepository implements IUserRepository, UserResponseCallback{

    private final MutableLiveData<Result> userMutableLiveData;
    private final BaseUserAuthenticationRemoteDataSource userRemoteDataSource;
    private final BaseUserDataRemoteDataSource userDataRemoteDataSource;

    public UserRepository(BaseUserAuthenticationRemoteDataSource userRemoteDataSource, BaseUserDataRemoteDataSource userDataRemoteDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
        this.userDataRemoteDataSource = userDataRemoteDataSource;
        this.userRemoteDataSource.setUserResponseCallback(this);
        this.userDataRemoteDataSource.setUserResponseCallback(this);
        userMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<Result> loginUser(String email, String password) {
        login(email, password);
        return userMutableLiveData;
    }

    @Override
    public void login(String email, String password) {
        userRemoteDataSource.login(email, password);
    }

    @Override
    public MutableLiveData<Result> registerUser(String nome, String cognome, String email, String password, int eta) {
        signUp(nome, cognome, email, password, eta);
        return userMutableLiveData;
    }

    @Override
    public void signUp(String nome, String cognome, String email, String password, int eta) {
        userRemoteDataSource.signUp(nome, cognome, email, password, eta);
    }



    @Override
    public void onSuccessFromAuthentication(User user) {
        if(user != null){
            userDataRemoteDataSource.saveUserData(user);
        }
    }

    @Override
    public void onFailureFromAuthentication(String message) {
        Result.Error result = new Result.Error(message);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onSuccessFromRemoteDatabase(User user) {
        Result.UserResponseSuccess result = new Result.UserResponseSuccess(user);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onFailureFromRemoteDatabase(String message) {
        Result.Error result = new Result.Error(message);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onSuccessLogout() {

    }
}
