package com.example.cinevibes.model;

public class Result {
    private Result(){}

    public boolean isSuccessUser() {
        return this instanceof UserResponseSuccess;
    }

    public boolean isError() {
        return this instanceof Error;
    }
    public static final class UserResponseSuccess extends Result {
        private final User user;
        public UserResponseSuccess(User user) {
            this.user = user;
        }
        public User getData() {
            return user;
        }
    }
    public static final class Error extends Result {
        private final String message;
        public Error(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }
}
