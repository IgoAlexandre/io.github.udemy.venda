package io.github.udemy.venda.domain.repository;

import io.github.udemy.venda.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Query Methods
    List<Cliente> findByNomeClienteLike(String nomeCliente);

    //Query Methods
    List<Cliente> findByIdCliente(Integer idCliente);

    //Query Methods
    List<Cliente> findByNomeClienteOrIdCliente(String nomeCliente, Integer idCliente);

    //Query Methods
    Cliente findOneByNomeCliente(String nomeCliente);

    //Query Methods
    boolean existsByNomeCliente(String nomeCliente);

    //Native Query, não é necessário visto que este metodo é uma Query Method
    @Query("delete from Cliente c where Nome_Cliente = :nomeCliente")
    @Modifying //Transacionar
    void deleteByNomeCliente(String nomeCliente);

    //Consulta jpql
    @Query(value = "select c from Cliente c where c.nomeCliente like %:nomeCliente%")
    List<Cliente> encontrarNomeCliente(@Param("nomeCliente") String nomeCliente);

    //Consulta sql, native query
    @Query(value = "select * from Cliente c where c.Nome_Cliente like %:nomeCliente%", nativeQuery = true)
    List<Cliente> encontrarNomeClienteNative(@Param("nomeCliente") String nomeCliente);

    @Query("select c from Cliente c left join fetch c.pedidos where c.idCliente = :idCliente")
    Cliente findClienteFecthPedidos(@Param("idCliente") Integer idCliente);
}
