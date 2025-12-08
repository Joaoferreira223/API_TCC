package com.reciclabet.reciclabet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeuControlador {

    @GetMapping("/") 
    public String saudacao() {
        return "Bem-vindo à aplicação SucataBook!";
    }








}

