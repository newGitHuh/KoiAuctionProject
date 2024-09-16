package com.demo.demo.responseStatus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class TokenResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private String userId;
}
