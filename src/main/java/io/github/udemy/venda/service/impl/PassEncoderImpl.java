package io.github.udemy.venda.service.impl;

import io.github.udemy.venda.service.PassEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PassEncoderImpl implements PassEncoder {
    @Override
    @Bean
    public PasswordEncoder retornarPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
