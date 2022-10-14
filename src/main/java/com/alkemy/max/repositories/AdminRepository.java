package com.alkemy.max.repositories;

import com.alkemy.max.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin findByEmail(String email);
}
