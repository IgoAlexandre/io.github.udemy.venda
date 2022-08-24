package io.github.udemy.venda.domain.entity;


public class Cliente {
    private Integer idCLiente;
    private String nomeCliente;

    public Cliente() {
    }

    public Cliente(Integer idCLiente) {
        this.idCLiente = idCLiente;
    }

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public Cliente(Integer idCLiente, String nomeCliente) {
        this.idCLiente = idCLiente;
        this.nomeCliente = nomeCliente;
    }
    public Integer getIdCLiente() {
        return idCLiente;
    }

    public void setIdCLiente(Integer idCLiente) {
        this.idCLiente = idCLiente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCLiente=" + idCLiente +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}
