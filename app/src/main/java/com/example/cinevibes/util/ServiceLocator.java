package com.example.cinevibes.util;

import android.app.Application;

import com.example.cinevibes.repository.user.IUserRepository;
import com.example.cinevibes.repository.user.UserRepository;
import com.example.cinevibes.source.user.BaseUserAuthenticationRemoteDataSource;
import com.example.cinevibes.source.user.BaseUserDataRemoteDataSource;
import com.example.cinevibes.source.user.UserAuthenticationRemoteDataSource;
import com.example.cinevibes.source.user.UserDataRemoteDataSource;

public class ServiceLocator {
    private static volatile ServiceLocator INSTANCE = null;

    private ServiceLocator() {}

    /**
     * Returns an instance of ServiceLocator class.
     * @return An instance of ServiceLocator.
     */
    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized(ServiceLocator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }

    public IUserRepository getUserRepository(Application application) {

        BaseUserAuthenticationRemoteDataSource userRemoteAuthenticationDataSource =
                new UserAuthenticationRemoteDataSource();


        BaseUserDataRemoteDataSource userDataRemoteDataSource =
                new UserDataRemoteDataSource();
        /*DataEncryptionUtil dataEncryptionUtil = new DataEncryptionUtil(application);
         */


        return new UserRepository(userRemoteAuthenticationDataSource, userDataRemoteDataSource);
    }
}
