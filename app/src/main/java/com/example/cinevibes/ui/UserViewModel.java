package com.example.cinevibes.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinevibes.model.Result;
import com.example.cinevibes.repository.user.IUserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private static final String TAG = UserViewModel.class.getSimpleName();
    private final IUserRepository userRepository;

    private MutableLiveData<Result> userMutableLiveData;

    public UserViewModel(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<Result> loginUserMutableLiveData(String email, String password) {
        loginUser(email, password);
        return userMutableLiveData;
    }
    public void loginUser(String email, String password) {
        userMutableLiveData = userRepository.loginUser(email, password);
    }

    public MutableLiveData<Result> registerUserMutableLiveData(String nome, String cognome, String email, String password, int eta) {
        if (userMutableLiveData == null) {
            registerUser(nome, cognome, email, password, eta);
        }
        return userMutableLiveData;
    }

    public void registerUser(String nome, String cognome, String email, String password, int eta) {
        userMutableLiveData = userRepository.registerUser(nome, cognome, email, password, eta);
    }

}
