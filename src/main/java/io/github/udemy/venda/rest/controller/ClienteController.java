package io.github.udemy.venda.rest.controller;

import io.github.udemy.venda.domain.entity.Cliente;
import io.github.udemy.venda.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
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
    @ResponseBody
    public String helloCliente(@PathVariable("nomeCliente") String nomeCliente){
        return String.format("Hello %s", nomeCliente);
    }

    @GetMapping("/api/clientes/{idCliente}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteByIdCliente(@PathVariable Integer idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{idCliente}")
    @ResponseBody
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Integer idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/clientes/{idCliente}")
    @ResponseBody
    public ResponseEntity atualizarCliente(@PathVariable Integer idCliente, @RequestBody Cliente cliente){
        return clienteRepository.findById(idCliente)
                .map(clienteExistente -> {
                    cliente.setIdCliente(clienteExistente.getIdCliente());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    @GetMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity find(Cliente clienteFiltro){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(clienteFiltro, matcher);
        List<Cliente> listaClientes = clienteRepository.findAll(example);
        return ResponseEntity.ok(listaClientes);
    }
}
