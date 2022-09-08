package io.github.udemy.venda.service.impl;

import io.github.udemy.venda.domain.entity.Cliente;
import io.github.udemy.venda.domain.entity.ItemPedido;
import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.domain.entity.Produto;
import io.github.udemy.venda.domain.enums.StatusPedido;
import io.github.udemy.venda.domain.repository.ClienteRepository;
import io.github.udemy.venda.domain.repository.ItemPedidoRepository;
import io.github.udemy.venda.domain.repository.PedidoRepository;
import io.github.udemy.venda.domain.repository.ProdutoRepository;
import io.github.udemy.venda.exception.PedidoNaoEncontradoException;
import io.github.udemy.venda.exception.RegraNegocioException;
import io.github.udemy.venda.rest.dto.ItemPedidoDTO;
import io.github.udemy.venda.rest.dto.PedidoDTO;
import io.github.udemy.venda.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteRepository clienteRepository,
                             ProdutoRepository produtoRepository,
                             ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    @Transactional
    public Pedido salvarPedido(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getIdCliente();
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() ->
                new RegraNegocioException("Código cliente inválido")
        );
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        List<ItemPedido> itensPedidos = converterItens(pedido, pedidoDTO.getItens());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedidos);

        pedido.setItens(itensPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer idPedido) {
        return pedidoRepository.findByIdPedidoFetchItens(idPedido) ;
    }

    @Override
    @Transactional
    public void atualizarStatus(Integer idPedido, StatusPedido statusPedido) {
        pedidoRepository.findByIdPedido(idPedido).map(pedido -> {
            pedido.setStatusPedido(statusPedido);
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado!"));
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw  new RegraNegocioException("Não é possível realizar o pedido sem itens.");
        }

        return itens.stream()
                .map(dto -> {
                    Integer idProduto = dto.getIdProduto();
                    Produto produto = produtoRepository
                        .findById(idProduto)
                        .orElseThrow(
                                () -> new RegraNegocioException("Código de produto inválido: " + idProduto));

                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setPedido(pedido);
                itemPedido.setQuantidade(dto.getQuantidade());
                itemPedido.setProduto(produto);

                return itemPedido;
        }).collect(Collectors.toList());
    }


}
