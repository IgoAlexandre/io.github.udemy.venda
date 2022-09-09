package io.github.udemy.venda.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Cliente")
    private Integer idCliente;
    @Column(name = "Nome_Cliente", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomeCliente;

    @Column(name = "cpf_Cnpj", length = 14)
    @NotEmpty(message = "{campo.cpf.obrigatorio}}")
    @CPF(message = "{campo.cpf.invalido}")
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

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

}
