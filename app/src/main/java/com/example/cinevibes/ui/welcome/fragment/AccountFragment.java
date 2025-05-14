package com.example.cinevibes.ui.welcome.fragment;

import static com.example.cinevibes.util.Constants.USE_NAVIGATION_COMPONENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cinevibes.R;
import com.example.cinevibes.model.Result;
import com.example.cinevibes.model.User;
import com.example.cinevibes.repository.user.IUserRepository;
import com.example.cinevibes.ui.UserViewModel;
import com.example.cinevibes.ui.UserViewModelFactory;
import com.example.cinevibes.ui.home.HomeActivity;
import com.example.cinevibes.util.ServiceLocator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    private TextInputEditText editTextEmail, editTextPassword, editTextNome, editTextCognome, editTextEta;
    private UserViewModel userViewModel;
    private String nome, cognome, email, password;
    private Button confermaAccountButton;
    int eta;
    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IUserRepository userRepository = ServiceLocator.getInstance().
                getUserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(
                this,
                new UserViewModelFactory(userRepository)).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        editTextEmail = view.findViewById(R.id.textInputEmailAccount);
        editTextPassword = view.findViewById(R.id.textInputPasswordAccount);
        editTextNome = view.findViewById(R.id.textInputNome);
        editTextCognome = view.findViewById(R.id.textInputCognome);
        editTextEta = view.findViewById(R.id.textInputEtà);
        confermaAccountButton = view.findViewById(R.id.confermaAccountButton);

        confermaAccountButton.setOnClickListener(v -> {
            if (editTextEmail.getText() != null && isEmailOk(editTextEmail.getText().toString())) {
                if (editTextPassword.getText() != null && isPasswordOk(editTextPassword.getText().toString())) {
                    nome = editTextNome.getText().toString();
                    cognome = editTextCognome.getText().toString();
                    email = editTextEmail.getText().toString();
                    password = editTextPassword.getText().toString();
                    eta = Integer.parseInt(editTextEta.getText().toString());
                    userViewModel.registerUserMutableLiveData(nome, cognome, email, password, eta).observe(
                            getViewLifecycleOwner(), result -> {
                                if (result.isSuccessUser()) {
                                    User user = ((Result.UserResponseSuccess) result).getData();
                                     retrieveUserInformationAndStartActivity(user, R.id.action_accountFragment_to_home2Activity);

                                } else {

                                    Snackbar.make(requireActivity().findViewById(android.R.id.content),
                                            getErrorMessage(((Result.Error) result).getMessage()),
                                            Snackbar.LENGTH_SHORT).show();
                                }
                            });


                } else {
                    editTextPassword.setError("La password deve avere almeno 8 caratteri");
                }
            } else {
                editTextEmail.setError("email non valida");
            }

        });

    }

    private void retrieveUserInformationAndStartActivity(User user, int destination) {
        startActivityBasedOnCondition(HomeActivity.class, destination);
    }

    private void startActivityBasedOnCondition(Class<?> destinationActivity, int destination) {
        if (USE_NAVIGATION_COMPONENT) {
            Navigation.findNavController(requireView()).navigate(destination);
        } else {
            Intent intent = new Intent(requireContext(), destinationActivity);
            startActivity(intent);
        }
        requireActivity().finish();
    }

    private String getErrorMessage(String errorType) {
        switch (errorType) {
             default:
                return requireActivity().getString(R.string.unexpected_error);
        }
    }


    private boolean isEmailOk (String email) {
        return EmailValidator.getInstance().isValid(email);
    }
    private boolean isPasswordOk (String password) {
        return password.length() > 7;
    }
}



