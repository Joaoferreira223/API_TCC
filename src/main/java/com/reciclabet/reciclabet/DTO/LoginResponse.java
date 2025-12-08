package com.reciclabet.reciclabet.DTO;

public class LoginResponse {
    private String mensagem;
    private String token;

    public LoginResponse(String mensagem, String token) {
        this.mensagem = mensagem;
        this.token = token;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getToken() {
        return token;
    }
}

