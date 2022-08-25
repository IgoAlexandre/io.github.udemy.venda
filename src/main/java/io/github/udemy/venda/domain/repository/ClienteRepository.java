package io.github.udemy.venda.domain.repository;

import io.github.udemy.venda.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClienteRepository {

   @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        //jdbcTemplate.update(INSERT, new Object[]{cliente.getNomeCliente()});
        return cliente;
    }

    @Transactional
    public void excluirCliente(Cliente cliente){
        if(!entityManager.contains(cliente)) {
            cliente = atualizarCliente(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void excluirCliente(Integer idCliente){
        Cliente cliente = entityManager.find(Cliente.class, idCliente);
        excluirCliente(cliente);
//        jdbcTemplate.update(DELETE_ONE, new Object[]{idCliente});
    }

    @Transactional
    public Cliente atualizarCliente(Cliente cliente){
        return entityManager.merge(cliente);
//        jdbcTemplate.update(UPDATE_ONE, new Object[]{cliente.getNomeCliente(), cliente.getIdCLiente()});
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarClientes(){
        return entityManager.createQuery("from Cliente",Cliente.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNomeCliente(Cliente cliente){
        String jpql = " SELECT c FROM Cliente c WHERE c.nomeCliente LIKE :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + cliente.getNomeCliente() + "%");

        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorIdCliente(Cliente cliente){
        String jpql = " SELECT c FROM Cliente c WHERE c.id = :id ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("id", cliente.getIdCLiente());

        return query.getResultList();
    }


}
