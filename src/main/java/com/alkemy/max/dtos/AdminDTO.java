package com.alkemy.max.dtos;

import com.alkemy.max.models.Admin;
import lombok.Getter;

@Getter
public class AdminDTO {
    private Long id;
    private String name, email;
    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.name = admin.getName();
    }
}
