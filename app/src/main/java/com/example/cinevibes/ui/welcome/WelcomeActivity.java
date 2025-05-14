package com.example.cinevibes.ui.welcome;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinevibes.R;
import com.example.cinevibes.repository.user.IUserRepository;
import com.example.cinevibes.ui.UserViewModel;
import com.example.cinevibes.ui.UserViewModelFactory;
import com.example.cinevibes.util.ServiceLocator;

public class WelcomeActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        IUserRepository userRepository = ServiceLocator.getInstance().
                getUserRepository(getApplication());
        userViewModel = new ViewModelProvider(
                this,
                new UserViewModelFactory(userRepository)).get(UserViewModel.class);


    }
}