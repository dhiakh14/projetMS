package com.esprit.pi.project.rest;

import com.esprit.pi.project.service.KeycloakRoleService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final KeycloakRoleService keycloakRoleService;

    public RoleController(KeycloakRoleService keycloakRoleService) {
        this.keycloakRoleService = keycloakRoleService;
    }

    @PostMapping("/assign/{username}/{roleName}")
    public ResponseEntity<String> assignRole(
            @PathVariable String username,
            @PathVariable String roleName
    ) {
        keycloakRoleService.assignRoleToUser(username, roleName);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<String>> getRolesByUserId(@PathVariable String userId) {
        List<String> roles = keycloakRoleService.getRolesByUserId(userId);
        return ResponseEntity.ok(roles);}

    @PostMapping("/create")
    public ResponseEntity<String> createRole(@RequestParam String roleName) {
        try {
            keycloakRoleService.createRole(roleName);
            return ResponseEntity.ok("Role " + roleName + " created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create role: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllRoles() {
        List<String> roles = keycloakRoleService.getAllRealmRoles();
        return ResponseEntity.ok(roles);
    }
}
