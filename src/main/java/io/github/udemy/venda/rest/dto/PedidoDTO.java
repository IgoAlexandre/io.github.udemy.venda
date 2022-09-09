package io.github.udemy.venda.rest.dto;

import io.github.udemy.venda.domain.enums.StatusPedido;
import io.github.udemy.venda.validation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer idCliente;

    @NotNull(message = "{campo.valor-total-pedido.obrigatorio}")
    private BigDecimal valorTotal;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;
}
