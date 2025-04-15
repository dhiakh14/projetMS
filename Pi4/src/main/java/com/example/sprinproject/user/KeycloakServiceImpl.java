package com.example.sprinproject.user;

import com.example.sprinproject.feign.RoleServiceClient;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.*;


import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j

public class KeycloakServiceImpl implements KeycloakUserService {

    private Keycloak keycloak ;
    private RoleServiceClient roleServiceClient;
    private UserRepository userRepository;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.server-url}")
    private String keycloakServerUrl;

    public KeycloakServiceImpl(Keycloak keycloak, UserRepository userRepository, RoleServiceClient roleServiceClient) {
        this.keycloak = keycloak;
        this.userRepository= userRepository;
        this.roleServiceClient=roleServiceClient;
    }

    @Override
    @Transactional
    public RegistrationRequest createUser(RegistrationRequest registrationRequest) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(registrationRequest.username());
        user.setEmail(registrationRequest.email());
        user.setFirstName(registrationRequest.firstName());
        user.setLastName(registrationRequest.lastName());
        user.setEmailVerified(false);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setValue(registrationRequest.password());
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        user.setCredentials(List.of(credential));

        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(user);

        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath()
                    .replaceAll(".*/([^/]+)$", "$1");

            User dbUser = new User();
            dbUser.setKeycloakId(userId);
            dbUser.setUsername(registrationRequest.username());
            dbUser.setEmail(registrationRequest.email());
            dbUser.setFirstName(registrationRequest.firstName());
            dbUser.setLastName(registrationRequest.lastName());
            userRepository.save(dbUser);

            usersResource.get(userId).executeActionsEmail(List.of("VERIFY_EMAIL"));


            return registrationRequest;
        } else {
            log.error("Keycloak user creation failed: {}", response.getStatusInfo());
            throw new RuntimeException("User creation failed");
        }
    }
    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        UsersResource usersResource = realm1.users();
        return usersResource;
    }



    @Override

    public void emailVerification(String userId){

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }


    @Override
    public UserRepresentation getUserById(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)){
            throw new RuntimeException("User not authenticated");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String currentUserId = jwt.getSubject();

        if (!currentUserId.equals(userId)) {
            throw new RuntimeException("Not authorized to access this user's data");
        }

        return keycloak.realm(realm)
                .users()
                .get(userId)
                .toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {
        getUsersResource().delete(userId);

    }

    @Override
    @Transactional
    public void updateUserById(String userId, UpdateUserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new RuntimeException("User not authenticated");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String currentUserId = jwt.getSubject();

        if (!currentUserId.equals(userId)) {
            throw new RuntimeException("Not authorized to update this user's data");
        }

        var userResource = keycloak.realm(realm).users().get(userId);
        var userRep = userResource.toRepresentation();

        userRep.setFirstName(request.firstName());
        userRep.setLastName(request.lastName());
        userRep.setEmail(request.email());
        userRep.setUsername(request.username());

        userResource.update(userRep);

        if (request.password() != null && !request.password().isBlank()) {
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setTemporary(false);
            credential.setValue(request.password());
            userResource.resetPassword(credential);
        }

        userRepository.findByKeycloakId(userId).ifPresent(dbUser -> {
            dbUser.setFirstName(request.firstName());
            dbUser.setLastName(request.lastName());
            dbUser.setEmail(request.email());
            dbUser.setUsername(request.username());
            userRepository.save(dbUser);
        });

        log.info("User with ID {} has been updated successfully", userId);
    }

    @Override

    public String getUserIdByUsername(String username) {
        List<UserRepresentation> users = keycloak.realm(realm)
                .users()
                .searchByUsername(username, true);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found: " + username);
        }
        return users.get(0).getId();
    }

    @Override
    public List<UserRepresentation> getAllUsersExceptCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new RuntimeException("User not authenticated");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String currentUserId = jwt.getSubject();

        List<UserRepresentation> allUsers = getUsersResource().list();
        List<UserRepresentation> filteredUsers = new ArrayList<>();

        for (UserRepresentation user : allUsers) {
            if (!Objects.equals(user.getId(), currentUserId)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }






    @Override
    public void forgotpassword(String username) {
        try {
            UsersResource usersResource = getUsersResource();
            List<UserRepresentation> representationList = usersResource.searchByUsername(username, true);

            if (representationList.isEmpty()) {
                log.error("Password reset failed: User {} not found", username);
                throw new RuntimeException("User not found");
            }

            UserRepresentation userRepresentation = representationList.get(0);
            UserResource userResource = usersResource.get(userRepresentation.getId());

            List<String> actions = new ArrayList<>();
            actions.add("UPDATE_PASSWORD");

            if (!userRepresentation.isEmailVerified()) {
                actions.add("VERIFY_EMAIL");
                log.info("Including email verification for user {}", username);
            }

            userResource.executeActionsEmail(actions);
            log.info("Password reset email sent successfully to {}", username);

        } catch (Exception e) {
            log.error("Failed to send password reset email for user: " + username, e);
            throw new RuntimeException("Failed to send password reset email. Please try again later.");
        }
    }

    @Override
    public List<String> getRolesByUserId(String userId) {
        try {
            log.debug("Fetching roles for user ID: {}", userId);
            List<String> roles = roleServiceClient.getRolesByUserId(userId);
            log.info("Successfully retrieved {} roles for user {}", roles.size(), userId);
            return roles;
        } catch (Exception e) {
            log.error("Failed to retrieve roles for user {}: {}", userId, e.getMessage());
            throw new RuntimeException("Failed to retrieve user roles", e);
        }
    }







}