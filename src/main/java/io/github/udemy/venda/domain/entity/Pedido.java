package io.github.udemy.venda.domain.entity;

import io.github.udemy.venda.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "data_Pedido")
    private LocalDate dataPedido;

    @Column(name = "valor_Total", precision = 20, scale = 2)
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<ItemPedido> itens;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusPedido statusPedido;

    public Pedido(Integer idPedido, Cliente cliente, LocalDate dataPedido, BigDecimal valorTotal) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }
}
