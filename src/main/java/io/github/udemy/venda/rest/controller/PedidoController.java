package io.github.udemy.venda.rest.controller;

import io.github.udemy.venda.domain.entity.ItemPedido;
import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.domain.enums.StatusPedido;
import io.github.udemy.venda.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.udemy.venda.rest.dto.InformacaoItemPedidoDTO;
import io.github.udemy.venda.rest.dto.InformacoesPedidoDTO;
import io.github.udemy.venda.rest.dto.PedidoDTO;
import io.github.udemy.venda.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{idPedido}")
    public InformacoesPedidoDTO obterPedidoCompleto(@PathVariable Integer idPedido){
        return pedidoService.obterPedidoCompleto(idPedido)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getIdPedido())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpfCnpj())
                .valorTotal(pedido.getValorTotal())
                .nomeCliente(pedido.getCliente().getNomeCliente())
                .status(pedido.getStatusPedido().name())
                .itens(converter(pedido.getItens())
                ).build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(item ->
            InformacaoItemPedidoDTO.builder()
                    .descricao(item.getProduto().getDescricao())
                    .precoUnitario(item.getProduto().getValorProduto())
                    .quantidade(item.getQuantidade())
                    .build()
        ).collect(Collectors.toList());
    }


    @PatchMapping(value = "{idPedido}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarPedido(@PathVariable Integer idPedido,
                              @RequestBody AtualizacaoStatusPedidoDTO atualizacaoStatusPedidoDTO){

        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
        pedidoService.atualizarStatus(idPedido, StatusPedido.valueOf(novoStatus));
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
