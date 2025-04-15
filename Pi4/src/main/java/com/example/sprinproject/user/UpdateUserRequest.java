package com.example.sprinproject.user;

public record UpdateUserRequest(
        String firstName,
        String lastName,
        String email,
        String username,
        String password
) {}
