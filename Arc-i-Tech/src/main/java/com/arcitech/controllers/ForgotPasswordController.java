/**
 * Copyright (c) 2024, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arcitech.exceptions.UserNotFoundException;
import com.arcitech.helper.SiteURL;
import com.arcitech.model.ForgotPasswordToken;
import com.arcitech.service.ForgotPasswordService;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/forgot_password/{email}/{username}")
    public ResponseEntity<Map<String, Object>> processForgotPassword(
            @PathVariable("email") String email,
            @PathVariable("username") String username,
            HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = RandomString.make(45);
            forgotPasswordService.updateRestPasswordToken(token, username);

            String resetPasswordLink = SiteURL.getSiteURL(request) + "/reset_password?token=" + token + "&password=";
            sendEmail(email, resetPasswordLink);

            response.put("username", username);
            response.put("message", "Reset password link has been sent to the provided email.");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception e) {
            response.put("username", username);
            response.put("error", "Token generation failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("support@arc-i-tech.in", "Arc-i-Tech Support");
        helper.setTo(email);
        helper.setSubject("Here's the link to reset your password");
        helper.setText("<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Change my Password</a></b></p>"
                + "<p>Ignore this email if you did not request a password reset.</p>", true);
        mailSender.send(message);
    }

    @GetMapping("/reset_password/{token}")
    public ResponseEntity<Map<String, Object>> resetPasswordProcess(@PathVariable("token") String token) {
        Map<String, Object> response = new HashMap<>();
        try {
            ForgotPasswordToken tokens = forgotPasswordService.validateToken(token);
            if (tokens == null) {
                response.put("token", token);
                response.put("error", "Invalid token or token expired.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            response.put("token", token);
            response.put("message", "Password reset token is valid.");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception e) {
            response.put("token", token);
            response.put("error", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/reset_password")
    public ResponseEntity<Map<String, Object>> resetPasswordProcess(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String token = request.getParameter("token");
            String password = request.getParameter("password");

            if (token == null || token.isEmpty() || password == null || password.isEmpty()) {
                response.put("error", "Token and password must be provided.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            ForgotPasswordToken forgotPasswordToken = forgotPasswordService.validateToken(token);
            forgotPasswordService.updatePassword(forgotPasswordToken, password);

            response.put("message", "Password has been reset successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException e) {
            response.put("error", "Invalid token or user not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("error", "An error occurred while resetting the password.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            
        }
    }
}
