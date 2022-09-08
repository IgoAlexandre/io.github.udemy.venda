package io.github.udemy.venda.service;

import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.domain.enums.StatusPedido;
import io.github.udemy.venda.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

     Pedido salvarPedido(PedidoDTO pedidoDTO);

     Optional<Pedido> obterPedidoCompleto(Integer idPedido);

     void atualizarStatus(Integer idPedido, StatusPedido statusPedido);
}
