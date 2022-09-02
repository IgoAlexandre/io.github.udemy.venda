package io.github.udemy.venda.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    public Pedido() {
    }

    public Pedido(Integer idPedido, Cliente cliente, LocalDate dataPedido, BigDecimal valorTotal, List<ItemPedido> itens) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public Pedido(Integer idPedido, Cliente cliente, LocalDate dataPedido, BigDecimal valorTotal) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", cliente=" + cliente +
                ", dataPedido=" + dataPedido +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
