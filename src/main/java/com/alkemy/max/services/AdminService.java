package com.alkemy.max.services;

import com.alkemy.max.models.Admin;

public interface AdminService {
    public Admin getById(Long id);
    public Admin getByEmail(String email);
    public void save(Admin admin);
    }
