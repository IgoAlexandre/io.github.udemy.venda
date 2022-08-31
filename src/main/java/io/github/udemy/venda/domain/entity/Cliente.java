package io.github.udemy.venda.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Cliente")
    private Integer idCliente;
    @Column(name = "Nome_Cliente", length = 100)
    private String nomeCliente;

    @Column(name = "cpf_Cnpj", length = 14)
    private String cpfCnpj;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public Cliente(Integer idCliente, String nomeCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
    }
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                '}';
    }
}
