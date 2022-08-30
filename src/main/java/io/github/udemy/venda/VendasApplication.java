package io.github.udemy.venda;

import io.github.udemy.venda.domain.entity.Cliente;
import io.github.udemy.venda.domain.entity.Pedido;
import io.github.udemy.venda.domain.repository.ClienteRepository;
import io.github.udemy.venda.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner executar(@Autowired ClienteRepository clienteRepository,
                                      @Autowired PedidoRepository pedidoRepository){
        return args -> {
            System.out.println("Salvando Clientes");
            clienteRepository.save(new Cliente("Luke"));
            clienteRepository.save(new Cliente("Thor"));
            clienteRepository.save(new Cliente("Igão"));

            Cliente cliente = clienteRepository.findOneByNomeCliente("Luke");



            List<Cliente> listaCliente = clienteRepository.findAll();
            listaCliente.forEach(System.out::println);

            System.out.println("#####################ClientefindByIdCliente####################");
            listaCliente = clienteRepository.findByIdCliente(1);
            listaCliente.forEach(System.out::println);
            System.out.println("#####################ClientefindByIdCliente####################");

            System.out.println("#####################ClienteencontrarNomeCliente####################");
//            List<Cliente> lista = clienteRepository.encontrarNomeCliente("%o%");
//            lista.forEach(c -> {
//                System.out.println(c.getNomeCliente());
//            });
            clienteRepository.encontrarNomeCliente("o").forEach(System.out::println);

            System.out.println("#####################ClienteencontrarNomeCliente####################");


            System.out.println("#####################ClienteencontrarNomeClienteNative####################");
//            List<Cliente> lista = clienteRepository.encontrarNomeCliente("%o%");
//            lista.forEach(c -> {
//                System.out.println(c.getNomeCliente());
//            });
            clienteRepository.encontrarNomeClienteNative("o").forEach(System.out::println);

            System.out.println("#####################ClienteencontrarNomeClienteNative####################");

            if(clienteRepository.existsByNomeCliente("Luke")){
                System.out.println("Cliente encontrado");
                clienteRepository.findByNomeClienteLike("%o%").forEach(System.out::println);
            } else {
                System.out.println("Cliente não encontrado");
            }

            listaCliente.forEach(c -> {
                c.setNomeCliente(c.getNomeCliente() + " atualizado");
                clienteRepository.save(c);
            });

            System.out.println("Atualizando Cliente");
            listaCliente = clienteRepository.findAll();
            listaCliente.forEach(System.out::println);

            System.out.println("Buscando Cliente");
            clienteRepository.findByNomeClienteLike("Luk").forEach(System.out::println);

//            System.out.println("Deletando Cliente");
//            clienteRepository.findAll().forEach(cliente -> {
//                clienteRepository.delete(cliente);
//            });

//            System.out.println("Deletando Cliente por nome");
//            clienteRepository.findByNomeClienteLike(new Cliente("Luk")).forEach(cliente -> {
//                System.out.println("Excluíndo Cliente: " + cliente.getNomeCliente());
//                clienteRepository.excluirCliente(cliente);
//            });

//            System.out.println("Deletando Cliente por id");
//            clienteRepository.buscarPorIdCliente(new Cliente(1)).forEach(cliente -> {
//                System.out.println("Excluíndo Cliente: " + cliente.getNomeCliente());
//                clienteRepository.excluirCliente(cliente);
//            });

            listaCliente = clienteRepository.findAll();
            if(listaCliente.isEmpty()){
                System.out.println("Não existem clienteRepository cadastrados!");
            } else {
                listaCliente.forEach(System.out::println);
            }

            System.out.println("##########################################PEDIDOS#########################################");

            Pedido pedido = new Pedido(1, cliente, LocalDate.now(), BigDecimal.valueOf(54.89));
            pedidoRepository.save(pedido);

            Pedido pedido2 = new Pedido(1, cliente, LocalDate.now(), BigDecimal.valueOf(9874165.89));
            pedidoRepository.save(pedido2);

            cliente = clienteRepository.findClienteFecthPedidos(cliente.getIdCliente());
            System.out.println("#####################PedidosfindClienteFecthPedidos####################");
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

            System.out.println("#####################PedidosfindByCliente####################");
            pedidoRepository.findByCliente(cliente).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}