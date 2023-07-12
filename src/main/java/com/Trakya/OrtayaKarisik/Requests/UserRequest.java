package com.Trakya.OrtayaKarisik.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    String userName;
    @NotBlank
    String password;
    @NotBlank
    String telNo;
    @NotBlank
    @Email
    String email;
}
