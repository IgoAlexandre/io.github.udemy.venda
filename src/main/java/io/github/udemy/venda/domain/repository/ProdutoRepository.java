package io.github.udemy.venda.domain.repository;

import io.github.udemy.venda.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


}
