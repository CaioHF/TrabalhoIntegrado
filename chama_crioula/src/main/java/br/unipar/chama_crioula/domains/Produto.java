package br.unipar.chama_crioula.domains;

import br.unipar.chama_crioula.enums.StatusProdutoEnum;
import java.math.BigDecimal;

public class Produto {
    
    private Long id;
    
    private String nome;
    
    private String descricao;
    
    private BigDecimal preco;
    
    private String imagem;
    
    private StatusProdutoEnum status;
    
    private Long idCategoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public StatusProdutoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusProdutoEnum status) {
        this.status = status;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", preco=" + preco
                + ", status=" + status + ", idCategoria=" + idCategoria + '}';
    }
    
}

