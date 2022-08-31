package io.github.udemy.venda.rest.controller;

import io.github.udemy.venda.domain.entity.Produto;
import io.github.udemy.venda.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController (ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("{idProduto}")
    public Produto getProdutoByIdProduto(@PathVariable Integer idProduto){
        return produtoRepository
                .findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")
                );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @DeleteMapping("{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Integer idProduto){
        produtoRepository
                .findById(idProduto).map(produto -> {
                    produtoRepository.delete(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable Integer idProduto, @RequestBody Produto produto){
        produtoRepository
                .findById(idProduto).map(produtoExistente -> {
                    produto.setIdProduto(produtoExistente.getIdProduto());
                    produtoRepository.save(produto);
                    return produto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto produtoFiltro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produtoFiltro, matcher);
        return produtoRepository.findAll(example);

    }
}
