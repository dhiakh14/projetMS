package com.example.sprinproject.user;

import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")

public class UserController {




    private final KeycloakUserService keycloakUserService;
    private final KeycloakAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authService.authenticateUser(request.username(), request.password());
            return ResponseEntity.ok(Map.of("access_token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }



    @PostMapping("/createuser")
    public RegistrationRequest createUser(@RequestBody RegistrationRequest registrationRequest){
        return keycloakUserService.createUser(registrationRequest);

    }

    @GetMapping("/me")
    public UserRepresentation getCurrentUser(Principal principal) {
        return keycloakUserService.getUserById(principal.getName());
    }

    @GetMapping("/others")
    public List<UserRepresentation> getAllUsersExceptCurrent() {
        return keycloakUserService.getAllUsersExceptCurrent();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
        keycloakUserService.updateUserById(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{username}")
    public String getUserIdByUsername(@PathVariable String username) {
        return keycloakUserService.getUserIdByUsername(username);
    }

    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        keycloakUserService.emailVerification(userId);
    }

    @PutMapping("/forgot-password/{username}")
    public void forgotpassword(@PathVariable String username) {
        keycloakUserService.forgotpassword(username);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public void deleteUser(@PathVariable String userId){
         keycloakUserService.deleteUserById(userId);

    }
    @GetMapping("/{userId}/roles")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable String userId) {
        try {
            List<String> roles = keycloakUserService.getRolesByUserId(userId);
            return ResponseEntity.ok(roles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }}


}