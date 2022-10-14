package com.alkemy.max.controllers;

import com.alkemy.max.models.Admin;
import com.alkemy.max.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void register(@RequestParam String email, @RequestParam String name, @RequestParam String password){
        Admin user = new Admin(name, email, passwordEncoder.encode(password));
        adminRepository.save(user);
    }

}
