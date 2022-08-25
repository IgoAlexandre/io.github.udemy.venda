package io.github.udemy.venda.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idCLiente;
    @Column(name = "Nome_Cliente", length = 100)
    private String nomeCliente;

    public Cliente() {
    }

    public Cliente(Integer idCLiente) {
        this.idCLiente = idCLiente;
    }

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public Cliente(Integer idCLiente, String nomeCliente) {
        this.idCLiente = idCLiente;
        this.nomeCliente = nomeCliente;
    }
    public Integer getIdCLiente() {
        return idCLiente;
    }

    public void setIdCLiente(Integer idCLiente) {
        this.idCLiente = idCLiente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCLiente=" + idCLiente +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}
