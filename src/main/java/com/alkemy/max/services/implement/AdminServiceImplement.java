package com.alkemy.max.services.implement;

import com.alkemy.max.models.Admin;
import com.alkemy.max.repositories.AdminRepository;
import com.alkemy.max.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplement implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin getById(Long id){
        return adminRepository.findById(id).orElse(null);
    }
    @Override
    public Admin getByEmail(String email){
        return adminRepository.findByEmail(email);
    }
    @Override
    public void save(Admin admin){
        adminRepository.save(admin);
    }

}
