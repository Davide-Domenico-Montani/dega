package com.example.cinevibes.source.user;

import static com.example.cinevibes.util.Constants.ERROR_LOGIN;
import static com.example.cinevibes.util.Constants.INVALID_CREDENTIALS_ERROR;
import static com.example.cinevibes.util.Constants.INVALID_USER_ERROR;
import static com.example.cinevibes.util.Constants.UNEXPECTED_ERROR;
import static com.example.cinevibes.util.Constants.USER_COLLISION_ERROR;
import static com.example.cinevibes.util.Constants.WEAK_PASSWORD_ERROR;

import android.util.Log;

import com.example.cinevibes.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class UserAuthenticationRemoteDataSource extends BaseUserAuthenticationRemoteDataSource {
    private static final String TAG = UserAuthenticationRemoteDataSource.class.getSimpleName();


    ;


    private final FirebaseAuth firebaseAuth;
    public UserAuthenticationRemoteDataSource() {
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    userResponseCallback.onSuccessFromAuthentication(
                            new User(firebaseUser.getUid(), email, password)
                    );
                } else {
                    userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
                }
            } else {
                userResponseCallback.onFailureFromAuthentication(ERROR_LOGIN);
            }
        });
    }

    @Override
    public void signUp(String nome, String cognome, String email, String password, int eta) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    userResponseCallback.onSuccessFromAuthentication(
                            new User(firebaseUser.getUid(), nome, cognome, email, password, eta)
                    );
                } else {
                    userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
                }
            } else {
                userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
            }
        });
    }


    private String getErrorMessage(Exception exception) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            return WEAK_PASSWORD_ERROR;
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            return INVALID_CREDENTIALS_ERROR;
        } else if (exception instanceof FirebaseAuthInvalidUserException) {
            return INVALID_USER_ERROR;
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            return USER_COLLISION_ERROR;
        }
        return UNEXPECTED_ERROR;
    }
}
