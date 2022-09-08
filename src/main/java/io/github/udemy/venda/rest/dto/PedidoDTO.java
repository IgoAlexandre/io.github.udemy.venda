package io.github.udemy.venda.rest.dto;

import io.github.udemy.venda.domain.enums.StatusPedido;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Integer idCliente;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itens;
}
