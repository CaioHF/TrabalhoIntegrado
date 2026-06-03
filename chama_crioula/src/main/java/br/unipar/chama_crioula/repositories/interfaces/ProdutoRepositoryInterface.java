package br.unipar.chama_crioula.repositories.interfaces;

import br.unipar.chama_crioula.domains.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProdutoRepositoryInterface {
    
    public Produto inserir(Produto produto) throws SQLException;
    
    public Produto atualizar(Produto produto) throws SQLException;
    
    public void deletar(Long id) throws SQLException;
    
    public Produto findById(Long id) throws SQLException;
    
    public ArrayList<Produto> listarTodos() throws SQLException;
    
}

