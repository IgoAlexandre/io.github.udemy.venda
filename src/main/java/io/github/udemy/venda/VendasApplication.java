package io.github.udemy.venda;

import io.github.udemy.venda.domain.entity.Cliente;
import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.domain.repository.ClienteRepository;
import io.github.udemy.venda.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner executar(@Autowired ClienteRepository clienteRepository,
                                      @Autowired PedidoRepository pedidoRepository){
        return args -> {
            System.out.println("Salvando Clientes");
            clienteRepository.save(new Cliente("Luke"));
            clienteRepository.save(new Cliente("Thor"));
            clienteRepository.save(new Cliente("Igão"));

            System.out.println(clienteRepository.findByIdCliente(1));


        };
    }*/

}