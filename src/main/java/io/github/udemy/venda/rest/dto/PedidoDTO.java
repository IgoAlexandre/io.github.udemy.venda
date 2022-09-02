package io.github.udemy.venda.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    private Integer idCliente;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itens;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "idCliente=" + idCliente +
                ", valorTotal=" + valorTotal +
                ", itens=" + itens +
                '}';
    }
}
