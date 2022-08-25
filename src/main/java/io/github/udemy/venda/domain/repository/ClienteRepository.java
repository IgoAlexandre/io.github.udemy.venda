package io.github.udemy.venda.domain.repository;

import io.github.udemy.venda.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Object> findByNomeClienteLike(String nomeCliente);

}
