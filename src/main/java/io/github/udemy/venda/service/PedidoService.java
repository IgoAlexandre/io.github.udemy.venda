package io.github.udemy.venda.service;

import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.rest.dto.PedidoDTO;

public interface PedidoService {

     Pedido salvarPedido(PedidoDTO pedidoDTO);
}
