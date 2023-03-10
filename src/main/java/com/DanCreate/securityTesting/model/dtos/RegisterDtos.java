package com.DanCreate.securityTesting.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDtos {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
