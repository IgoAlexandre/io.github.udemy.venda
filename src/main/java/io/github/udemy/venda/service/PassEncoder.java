package io.github.udemy.venda.service;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PassEncoder {

    PasswordEncoder retornarPasswordEncoder();
}
