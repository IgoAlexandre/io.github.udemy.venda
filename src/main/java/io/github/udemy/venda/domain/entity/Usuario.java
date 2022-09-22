package io.github.udemy.venda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "login")
    @NotEmpty(message ="{campo.login.obrigatorio}")
    private String login;

    @Column(name = "senha")
    @NotEmpty(message ="{campo.senha.obrigatorio}")
    private String senha;

    @Column(name = "admin")
    private boolean admin;
}
