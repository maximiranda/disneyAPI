package com.alkemy.max.controllers;

import com.alkemy.max.dtos.AdminDTO;
import com.alkemy.max.models.Admin;
import com.alkemy.max.repositories.AdminRepository;
import com.alkemy.max.services.AdminService;
import com.alkemy.max.utils.VerificationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private VerificationEmail verificationEmail;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestParam String email, @RequestParam String name, @RequestParam String password){
        if (email.isEmpty() || name.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        if (adminService.getByEmail(email) != null){
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }
        Admin user = new Admin(name, email, passwordEncoder.encode(password));
        adminService.save(user);
        String publicAddress = "http://localhost:8080/";
        String subject = "Welcome To Disney";
        String body = "Hi, we're glad you're here. Please click the link below to confirm your email. " + publicAddress + user.getId();
        verificationEmail.sendEmail(user.getEmail(),subject, body);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }
    @GetMapping("/users/current")
    public AdminDTO get(Authentication authentication){
        Admin user = adminService.getByEmail(authentication.getName());
        return new AdminDTO(user);
    }

}
