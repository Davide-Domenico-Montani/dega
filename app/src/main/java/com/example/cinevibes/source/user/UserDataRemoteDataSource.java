package com.example.cinevibes.source.user;

import static com.example.cinevibes.util.Constants.USER_DATABASE_REFERENCE;

import androidx.annotation.NonNull;

import com.example.cinevibes.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataRemoteDataSource extends BaseUserDataRemoteDataSource {
    private static final String TAG = UserDataRemoteDataSource.class.getSimpleName();



    private final DatabaseReference databaseReference;

    public UserDataRemoteDataSource() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().getRef();

    }

    @Override
    public void saveUserData(User user) {
        databaseReference.child(USER_DATABASE_REFERENCE).child(user.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    userResponseCallback.onSuccessFromRemoteDatabase(user);
                }else{
                    databaseReference.child(USER_DATABASE_REFERENCE).child(user.getUserId()).setValue(user);
                    userResponseCallback.onSuccessFromRemoteDatabase(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userResponseCallback.onFailureFromRemoteDatabase(error.getMessage());
            }
        });
    }
}
