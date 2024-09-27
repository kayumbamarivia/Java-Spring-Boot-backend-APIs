package com.jmv.studentManagement.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    private final String secretKey;

    public JwtConfig(@Value("5fa46fd161a8d16819611cdcdc8163c6711631da11dbda06cb04f387a01ae333") String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

