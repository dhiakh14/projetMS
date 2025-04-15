package com.esprit.pi.project.service;

import com.esprit.pi.project.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakRoleService {
    private final Keycloak keycloak;
    private final UserServiceClient userServiceClient;

    public KeycloakRoleService(Keycloak keycloak, UserServiceClient userServiceClient) {
        this.keycloak = keycloak;
        this.userServiceClient = userServiceClient;
    }



    @Value("${keycloak.realm}")
    private String realm;

    public void createRole(String roleName) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleName);
        role.setDescription("Description of the role " + roleName);

        List<String> existingRoles = getAllRealmRoles();
        if (!existingRoles.contains(roleName)) {
            getRolesResource().create(role);
        } else {
            System.out.println("Role " + roleName + " already exists.");
        }
    }




    public List<String> getAllRealmRoles() {
        return getRolesResource().list()
                .stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }

    private RolesResource getRolesResource() {
        return keycloak.realm(realm).roles();
    }



    public void assignRoleToUser(String username, String roleName) {
        String userId = userServiceClient.getUserIdByUsername(username);

        UserResource userResource = keycloak.realm(realm).users().get(userId);
        RoleRepresentation role = keycloak.realm(realm).roles().get(roleName).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(role));
    }

    public List<String> getRolesByUserId(String userId) {
        UserResource userResource = keycloak.realm(realm).users().get(userId);
        return userResource.roles().realmLevel().listAll().stream()
                .map(RoleRepresentation::getName)
                .collect(Collectors.toList());
    }
}