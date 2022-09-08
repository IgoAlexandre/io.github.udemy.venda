package io.github.udemy.venda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Produto")
    private Integer idProduto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_Produto")
    private BigDecimal valorProduto;

}
