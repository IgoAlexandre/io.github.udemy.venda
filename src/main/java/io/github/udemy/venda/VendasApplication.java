package io.github.udemy.venda;

import io.github.udemy.venda.domain.entity.Cliente;
import io.github.udemy.venda.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner executar(@Autowired ClienteRepository clientes){
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.salvar(new Cliente("Luke"));
            clientes.salvar(new Cliente("Thor"));
            clientes.salvar(new Cliente("Igão"));

            List<Cliente> listaCliente = clientes.listarClientes();
            listaCliente.forEach(System.out::println);

            listaCliente.forEach(c -> {
                c.setNomeCliente(c.getNomeCliente() + " atualizado");
                clientes.atualizarCliente(c);
            });

            System.out.println("Atualizando Cliente");
            listaCliente = clientes.listarClientes();
            listaCliente.forEach(System.out::println);

            System.out.println("Buscando Cliente");
            clientes.buscarPorNomeCliente(new Cliente("Luk")).forEach(System.out::println);

//            System.out.println("Deletando Cliente");
//            clientes.listarClientes().forEach(cliente -> {
//                clientes.excluirCliente(cliente);
//            });

            System.out.println("Deletando Cliente por nome");
            clientes.buscarPorNomeCliente(new Cliente("Luk")).forEach(cliente -> {
                System.out.println("Excluíndo Cliente: " + cliente.getNomeCliente());
                clientes.excluirCliente(cliente);
            });

            System.out.println("Deletando Cliente por id");
            clientes.buscarPorIdCliente(new Cliente(1)).forEach(cliente -> {
                System.out.println("Excluíndo Cliente: " + cliente.getNomeCliente());
                clientes.excluirCliente(cliente);
            });

            listaCliente = clientes.listarClientes();
            if(listaCliente.isEmpty()){
                System.out.println("Não existem clientes cadastrados!");
            } else {
                listaCliente.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}