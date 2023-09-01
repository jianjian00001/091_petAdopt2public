package com.qingge.springboot.controller.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String username;
    private String phone;
    private String password;
    private String newPassword;
}
