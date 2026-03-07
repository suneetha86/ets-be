package com.ets.service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ets.enums.Role;
import com.ets.model.AdminLoginUser;
import com.ets.model.PasswordResetToken;
import com.ets.repository.AdminLoginUserRepository;
import com.ets.repository.PasswordResetTokenRepository;

@Service
public class AdminLoginService {

    @Autowired
    private AdminLoginUserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    // ADMIN LOGIN
    public String adminLogin(String username, String password) {

        AdminLoginUser user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account disabled");
        }

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Access denied (Admin only)");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return "LOGIN SUCCESS";
    }

    // FORGOT PASSWORD
    public String forgotPassword(String usernameOrEmail) {

        AdminLoginUser user = userRepository.findByUsernameIgnoreCase(usernameOrEmail)
                .or(() -> userRepository.findByEmailIgnoreCase(usernameOrEmail))
                .orElse(null);

        if (user == null) {
            return "If account exists, reset link sent.";
        }

        String token = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plus(Duration.ofMinutes(20));

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiresAt(expiresAt);
        resetToken.setUsed(false);

        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Password Reset");
        message.setText("Click this link to reset your password:\n" + resetLink);

        mailSender.send(message);

        return "Reset link sent to email";
    }

    // RESET PASSWORD
//    public String resetPassword(String token, String newPassword) {
//
//        PasswordResetToken resetToken = tokenRepository.findByToken(token)
//                .orElseThrow(() -> new RuntimeException("Invalid token"));
//
//        if (resetToken.isUsed()) {
//            throw new RuntimeException("Token already used");
//        }
//
//        if (resetToken.getExpiresAt().isBefore(Instant.now())) {
//            throw new RuntimeException("Token expired");
//        }
//
//        AdminLoginUser user = resetToken.getUser();
//        user.setPasswordHash(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//        resetToken.setUsed(true);
//        tokenRepository.save(resetToken);
//
//        return "Password updated successfully";
//    }
    
    public String resetPassword(String token, String newPassword) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElse(null);

        if (resetToken == null) {
            return "Invalid token";
        }

        if (resetToken.isUsed()) {
            return "Token already used";
        }

        if (resetToken.getExpiresAt().isBefore(Instant.now())) {
            return "Token expired";
        }

        AdminLoginUser user = resetToken.getUser();
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        resetToken.setUsed(true);
        tokenRepository.save(resetToken);

        return "Password reset successful";
    }}