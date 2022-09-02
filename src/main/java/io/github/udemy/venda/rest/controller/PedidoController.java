package io.github.udemy.venda.rest.controller;

import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.domain.repository.PedidoRepository;
import io.github.udemy.venda.rest.dto.PedidoDTO;
import io.github.udemy.venda.service.PedidoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvarPedido(pedidoDTO);
        return pedido.getIdPedido();
    }

    /*private PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvarPedido(@RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping(value = "{idPedido}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Integer idPedido){
        pedidoRepository.findById(idPedido).map(pedido -> {
            pedidoRepository.delete(pedido);
            return pedido;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PutMapping(value = "{idPedido}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pedido alterarPedido(@PathVariable Integer idPedido, @RequestBody Pedido pedido){
        return pedidoRepository.findById(idPedido).map(pedidoExistente -> {
            pedido.setIdPedido(idPedido);
            pedidoRepository.save(pedido);
            return pedido;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @GetMapping(value = "{idProduto}")
    public Pedido getProdutoByIdProduto(@PathVariable Integer idProduto){
        return pedidoRepository.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @GetMapping
    public List<Pedido> findAll(Pedido pedidoFiltro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(pedidoFiltro, matcher);
        return pedidoRepository.findAll(example);
    }*/

}
