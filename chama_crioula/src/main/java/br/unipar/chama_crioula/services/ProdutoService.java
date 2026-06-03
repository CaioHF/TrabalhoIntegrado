package br.unipar.chama_crioula.services;

import br.unipar.chama_crioula.domains.Produto;
import br.unipar.chama_crioula.exceptions.NaoEncontradoException;
import br.unipar.chama_crioula.exceptions.ValidacaoNegocioException;
import br.unipar.chama_crioula.repositories.ProdutoRepository;
import br.unipar.chama_crioula.repositories.interfaces.ProdutoRepositoryInterface;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoService {
    
    private ProdutoRepositoryInterface produtoRepositoryInterface;
    
    public ProdutoService() {
        this.produtoRepositoryInterface = new ProdutoRepository();
    }
    
    public Produto inserir(Produto produto) throws ValidacaoNegocioException, SQLException {
        
        validar(produto);
        
        produto = produtoRepositoryInterface.inserir(produto);
        
        return produto;
        
    }
    
    public Produto atualizar(Produto produto) throws ValidacaoNegocioException, NaoEncontradoException, SQLException {
        
        if (produto == null || produto.getId() == null) {
            throw new ValidacaoNegocioException("Obrigatorio informar o ID do Produto para atualizacao");
        }
        
        Produto produtoValidacao = produtoRepositoryInterface.findById(produto.getId());
        
        if (produtoValidacao == null) {
            throw new NaoEncontradoException("Produto nao encontrado para o ID informado");
        }
        
        validar(produto);
        
        produto = produtoRepositoryInterface.atualizar(produto);
        
        return produto;
        
    }
    
    public void deletar(Long id) throws ValidacaoNegocioException, NaoEncontradoException, SQLException {
        
        Produto produto = findById(id);
        
        produtoRepositoryInterface.deletar(produto.getId());
        
    }
    
    public Produto findById(Long id) throws ValidacaoNegocioException, NaoEncontradoException, SQLException {
        
        if (id == null) {
            throw new ValidacaoNegocioException("Obrigatorio informar o ID do Produto para busca");
        }
        
        Produto produto = produtoRepositoryInterface.findById(id);
        
        if (produto == null) {
            throw new NaoEncontradoException("Produto nao encontrado para o ID informado");
        }
        
        return produto;
        
    }
    
    public ArrayList<Produto> listarTodos() throws SQLException {
        return produtoRepositoryInterface.listarTodos();
    }
    
    private void validar(Produto produto) throws ValidacaoNegocioException {
        
        if (produto == null) {
            throw new ValidacaoNegocioException("Obrigatorio informar o Produto");
        }
        
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ValidacaoNegocioException("Informe o Nome do Produto");
        }
        
        if (produto.getNome().length() < 2) {
            throw new ValidacaoNegocioException("O nome do Produto precisa conter dois ou mais caracteres");
        }
        
        if (produto.getNome().length() > 120) {
            throw new ValidacaoNegocioException("O nome do Produto deve conter menos que 120 caracteres");
        }
        
        if (produto.getDescricao() == null || produto.getDescricao().isBlank()) {
            throw new ValidacaoNegocioException("Informe a Descricao do Produto");
        }
        
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidacaoNegocioException("O Preco do Produto deve ser maior que zero");
        }
        
        if (produto.getStatus() == null) {
            throw new ValidacaoNegocioException("Informe o Status do Produto");
        }
        
        if (produto.getIdCategoria() == null) {
            throw new ValidacaoNegocioException("Informe a Categoria do Produto");
        }
        
    }
    
}

