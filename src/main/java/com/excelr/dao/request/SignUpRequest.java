package com.excelr.dao.request;

import com.excelr.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String username;
    private String email;
    private String password;
    private Set<Role> roles;


}
