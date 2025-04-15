package com.example.sprinproject.auth;

import com.example.sprinproject.email.EmailService;
import com.example.sprinproject.email.EmailTemplateName;
import com.example.sprinproject.role.Role;
import com.example.sprinproject.role.RoleRepository;
import com.example.sprinproject.security.JwtService;
import com.example.sprinproject.user.Token;
import com.example.sprinproject.user.TokenRepository;
import com.example.sprinproject.user.User;
import com.example.sprinproject.user.userRepository;
<<<<<<< HEAD
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
=======
>>>>>>> origin/lahmer
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
>>>>>>> origin/lahmer
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


<<<<<<< HEAD
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
=======
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
>>>>>>> origin/lahmer
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final RoleRepository role;
    private final PasswordEncoder passwordEncoder;
    private final userRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
<<<<<<< HEAD
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${application.mailing.frontend.reset-password-url}")
    private String resetPasswordUrl;

    public AuthenficationResponse authenticateWithGoogle(String googleToken) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()
        )
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(googleToken);
        if (idToken == null) {
            throw new RuntimeException("Invalid Google token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String firstName = (String) payload.get("given_name");
        String lastName = (String) payload.get("family_name");

        LocalDate defaultDateOfBirth = LocalDate.of(2000, 1, 1);

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    Role userRole = role.findByName("USER")
                            .orElseThrow(() -> new IllegalStateException("ROLE_USER not found"));

                    User newUser = new User();
                    newUser.setFirstName(firstName);
                    newUser.setLastName(lastName);
                    newUser.setEmail(email);
                    newUser.setDateOfBirth(defaultDateOfBirth);
                    newUser.setPassword(passwordEncoder.encode(generateRandomPassword()));
                    newUser.setAccountLocked(false);
                    newUser.setEnabled(true);

                    Set<Role> roles = new HashSet<>();
                    roles.add(userRole);
                    newUser.setRoles(roles);

                    return userRepository.save(newUser);
                });

        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", user.FullName());
        claims.put("email", user.getEmail());
        claims.put("dateOfBirth", user.getDateOfBirth());

        String jwtToken = jwtService.generateToken(claims, user);
        return AuthenficationResponse.builder().token(jwtToken).build();
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
=======
>>>>>>> origin/lahmer




    public void register(RegistrationRequest request, List<String> roleNames) throws MessagingException {
        List<Role> roles = roleNames.stream()
                .map(roleName -> role.findByName(roleName.toUpperCase())
                        .orElseThrow(() -> new IllegalStateException("Role " + roleName + " was not initialized")))
                .collect(Collectors.toList());

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(new HashSet<>(roles))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }


    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.SendEmail(
                user.getEmail(),
                user.FullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken =generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codebuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i=0 ; i < length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codebuilder.append(characters.charAt(randomIndex));
        }
        return codebuilder.toString();


    }

    public AuthenficationResponse authenficate(AuthenficationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        var claims= new HashMap<String , Object>();
        var user = ((User)auth.getPrincipal());
        claims.put("fullName", user.FullName());
        claims.put("dateOfBirth", user.getDateOfBirth());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenficationResponse.builder().token(jwtToken).build();
    }


    public void activateaccount(String token) throws MessagingException {
        Token savedToken= tokenRepository.findByToken(token).orElseThrow(()-> new RuntimeException("Invalid Token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation Token Has Expired. A new Token has been send to the same email address");

        }
        var user = userRepository.findById(savedToken.getUser().getIdUser()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

    }
<<<<<<< HEAD



    public ResponseEntity<String> forgotPassword(String email) throws MessagingException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String resetToken = UUID.randomUUID().toString();
        Token token = Token.builder()
                .token(resetToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .user(user)
                .build();

        tokenRepository.save(token);

        String resetLink = resetPasswordUrl + "?token=" + resetToken;

        emailService.SendEmail(
                user.getEmail(),
                user.FullName(),
                EmailTemplateName.RESET_PASSWORD,
                resetLink,
                resetToken,
                "Password Reset Request"
        );

        return ResponseEntity.status(HttpStatus.OK).body("Password reset email sent successfully.");
    }


    public void resetPassword(String token, String newPassword) {
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            throw new RuntimeException("Token has expired");
        }

        try {
            User user = savedToken.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            tokenRepository.delete(savedToken);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update password. Please try again.");
        }
    }

=======
>>>>>>> origin/lahmer
}