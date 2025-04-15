package com.example.sprinproject.user;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Map;

public interface KeycloakUserService {

    RegistrationRequest createUser(RegistrationRequest registrationRequest);
    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);
     void updateUserById(String userId, UpdateUserRequest request) ;

    String getUserIdByUsername(String username);
     void emailVerification(String userId);
    void forgotpassword(String username);
     List<String> getRolesByUserId(String userId);
    List<UserRepresentation> getAllUsersExceptCurrent();





}
