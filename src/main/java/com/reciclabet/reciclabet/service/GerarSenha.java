package com.reciclabet.reciclabet.service;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {
    public static void main(String[] args) {
        // Sua senha em texto plano
        String senha = "requeijaoemelhor";

        // Criptografando com BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(senha);

        // Mostra o hash no console
        System.out.println("Senha criptografada: " + hash);
    }
}
