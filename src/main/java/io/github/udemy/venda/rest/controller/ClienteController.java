package io.github.udemy.venda.rest.controller;

import io.github.udemy.venda.domain.entity.Cliente;
import io.github.udemy.venda.domain.repository.ClienteRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController (ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @RequestMapping(
            value = "/hello/{nomeCliente}",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"}, //forma como pode receber a solicitação
            produces = {"application/json", "application/xml"} //forma como vai retornar o resultado
    )
    public String helloCliente(@PathVariable("nomeCliente") String nomeCliente){
        return String.format("Hello %s", nomeCliente);
    }

    @GetMapping("{idCliente}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente Encontrado"),
            @ApiResponse(code = 404, message = "Cliente não Encontrado para o Id informado")
    })
    public Cliente getClienteByIdCliente(@PathVariable @ApiParam("Id do Cliente") Integer idCliente){
        return clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
            );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Cliente salvarCliente(@RequestBody @Valid Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Integer idCliente){
         clienteRepository.findById(idCliente)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
        );
    }

    @PutMapping("{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Integer idCliente, @RequestBody @Valid Cliente cliente){
         clienteRepository.findById(idCliente)
                .map(clienteExistente -> {
                    cliente.setIdCliente(clienteExistente.getIdCliente());
                    clienteRepository.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


    @GetMapping
    public List<Cliente> find(Cliente clienteFiltro){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(clienteFiltro, matcher);
        return clienteRepository.findAll(example);
    }
}
