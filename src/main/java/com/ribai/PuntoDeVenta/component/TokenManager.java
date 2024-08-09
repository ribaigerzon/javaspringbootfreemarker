package com.ribai.PuntoDeVenta.component;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
