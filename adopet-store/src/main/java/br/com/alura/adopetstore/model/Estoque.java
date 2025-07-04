package br.com.alura.adopetstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.DialectOverride;
import org.springframework.data.annotation.Version;

import java.util.Objects;

@Entity
@Table(name = "estoques")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    @OneToOne
    private Produto produto;
    @Version
    private Integer versao;

    public Estoque(){}

    public Estoque(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.versao = 0;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getVersao() {
        return versao;
    }

    public Produto getProduto() {
        return produto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void adicionar(Integer quantidade) {
        this.quantidade += quantidade;
    }
    public void diminuir(Integer quantidade) {
        this.quantidade -= quantidade;
    }
}
