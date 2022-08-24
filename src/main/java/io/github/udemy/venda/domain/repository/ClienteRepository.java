package io.github.udemy.venda.domain.repository;

import io.github.udemy.venda.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

    private static String INSERT = "INSERT INTO CLIENTE (NOMECLIENTE) VALUES (?);";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String DELETE_ONE = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?;";
    private static String UPDATE_ONE = "UPDATE CLIENTE SET NOMECLIENTE = ? WHERE IDCLIENTE = ?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNomeCliente()});
        return cliente;
    }

    public List<Cliente> listarClientes(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteRowMapper());
    }

    public void excluirCliente(Cliente cliente){
        excluirCliente(cliente.getIdCLiente());
    }

    public void excluirCliente(Integer idCliente){
        jdbcTemplate.update(DELETE_ONE, new Object[]{idCliente});
    }

    public void atualizarCliente(Cliente cliente){
        jdbcTemplate.update(UPDATE_ONE, new Object[]{cliente.getNomeCliente(), cliente.getIdCLiente()});
    }

    public List<Cliente> buscarPorNomeCliente(Cliente cliente){
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE NOMECLIENTE LIKE ?"),
                new Object[]{"%" + cliente.getNomeCliente() + "%"},
                obterClienteRowMapper());
    }

    public List<Cliente> buscarPorIdCliente(Cliente cliente){
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE IDCLIENTE = ?"),
                new Object[]{cliente.getIdCLiente()},
                obterClienteRowMapper());
    }

    private static RowMapper<Cliente> obterClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(rs.getInt("idCliente"), rs.getString("nomeCliente"));
            }
        };
    }
}
