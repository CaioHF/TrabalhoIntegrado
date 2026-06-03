package br.unipar.chama_crioula.repositories;

import br.unipar.chama_crioula.domains.Produto;
import br.unipar.chama_crioula.enums.StatusProdutoEnum;
import br.unipar.chama_crioula.infraestructure.ConnectionFactory;
import br.unipar.chama_crioula.repositories.interfaces.ProdutoRepositoryInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoRepository implements ProdutoRepositoryInterface {
    
    private static final String INSERT = 
            "INSERT INTO produto (nome, descricao, preco, imagem, ativo, id_categoria) "
            + "VALUES (?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = 
            "UPDATE produto SET nome = ?, descricao = ?, preco = ?, imagem = ?, "
            + "ativo = ?, id_categoria = ? WHERE id_produto = ?;";
    
    private static final String DELETE = 
            "DELETE FROM produto WHERE id_produto = ?;";
    
    private static final String FIND_BY_ID = 
            "SELECT id_produto, nome, descricao, preco, imagem, ativo, id_categoria "
            + "FROM produto WHERE id_produto = ?;";
    
    private static final String FIND_ALL = 
            "SELECT id_produto, nome, descricao, preco, imagem, ativo, id_categoria "
            + "FROM produto ORDER BY nome;";

    @Override
    public Produto inserir(Produto produto) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstm = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setBigDecimal(3, produto.getPreco());
            pstm.setString(4, produto.getImagem());
            pstm.setBoolean(5, produto.getStatus() == StatusProdutoEnum.ATIVO);
            pstm.setLong(6, produto.getIdCategoria());
            
            pstm.executeUpdate();
            
            rs = pstm.getGeneratedKeys();
            
            if (rs.next()) produto.setId(rs.getLong("id_produto"));
            
        } finally {
            
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
            
        }
        
        return produto;
        
    }

    @Override
    public Produto atualizar(Produto produto) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setBigDecimal(3, produto.getPreco());
            pstm.setString(4, produto.getImagem());
            pstm.setBoolean(5, produto.getStatus() == StatusProdutoEnum.ATIVO);
            pstm.setLong(6, produto.getIdCategoria());
            pstm.setLong(7, produto.getId());
            
            pstm.executeUpdate();
            
        } finally {
            
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
            
        }
        
        return produto;
        
    }

    @Override
    public void deletar(Long id) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstm = conn.prepareStatement(DELETE);
            pstm.setLong(1, id);
            
            pstm.executeUpdate();
            
        } finally {
            
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
            
        }
        
    }

    @Override
    public Produto findById(Long id) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Produto produto = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setLong(1, id);
            
            rs = pstm.executeQuery();
            
            if (rs.next()) produto = criarProduto(rs);
            
        } finally {
            
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
            
        }
        
        return produto;
        
    }

    @Override
    public ArrayList<Produto> listarTodos() throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstm = conn.prepareStatement(FIND_ALL);
            
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Produto produto = criarProduto(rs);
                listaProdutos.add(produto);
            }
            
        } finally {
            
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
            
        }
        
        return listaProdutos;
        
    }
    
    private Produto criarProduto(ResultSet rs) throws SQLException {
        
        Produto produto = new Produto();
        produto.setId(rs.getLong("id_produto"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getBigDecimal("preco"));
        produto.setImagem(rs.getString("imagem"));
        produto.setStatus(rs.getBoolean("ativo") ? StatusProdutoEnum.ATIVO : StatusProdutoEnum.INATIVO);
        produto.setIdCategoria(rs.getLong("id_categoria"));
        
        return produto;
        
    }
    
}

