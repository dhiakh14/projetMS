package com.example.sprinproject.auth;
<<<<<<< HEAD
import com.example.sprinproject.user.Token;
=======
>>>>>>> origin/lahmer
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
=======
import java.util.List;
>>>>>>> origin/lahmer

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationService authService;


    @PostMapping("/Register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        List<String> roles = List.of("USER");
        authService.register(request, roles);
        return ResponseEntity.accepted().build();

    }
<<<<<<< HEAD
    @PostMapping("/google")
    public ResponseEntity<AuthenficationResponse> authenticateWithGoogle(
            @RequestParam String  googleToken
    ) throws IOException, GeneralSecurityException {
        return ResponseEntity.ok(authService.authenticateWithGoogle(googleToken));
    }


=======
>>>>>>> origin/lahmer
    @PostMapping("/authenticate")
    private ResponseEntity<AuthenficationResponse> authenticate(
            @RequestBody @Valid AuthenficationRequest request
    ){
        return ResponseEntity.ok(authService.authenficate(request));
    }
    @GetMapping("activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        authService.activateaccount(token);
    }
<<<<<<< HEAD

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
        authService.forgotPassword(email);
        return ResponseEntity.ok("Password reset link sent to email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword
    ) {
        try {
            authService.resetPassword(token, newPassword);
            return ResponseEntity.ok().body(
                    Map.of("message", "Password has been reset successfully")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        }
    }
=======
>>>>>>> origin/lahmer
}