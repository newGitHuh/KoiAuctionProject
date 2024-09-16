package com.demo.demo.requestDto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginRequest implements Serializable {

    private String username;

    private String password;
}
