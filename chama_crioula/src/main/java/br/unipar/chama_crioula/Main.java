package br.unipar.chama_crioula;

import br.unipar.chama_crioula.domains.Produto;
import br.unipar.chama_crioula.enums.StatusProdutoEnum;
import br.unipar.chama_crioula.services.ProdutoService;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        try {
            
            ProdutoService produtoService = new ProdutoService();
            
            System.out.println("CASO DE USO - CADASTRAR PRODUTO");
            Produto produto = new Produto();
            produto.setNome("Cupim");
            produto.setDescricao("Cupim bovino para assar");
            produto.setPreco(new BigDecimal("200.90"));
            produto.setImagem("cupim.jpg");
            produto.setStatus(StatusProdutoEnum.ATIVO);
            produto.setIdCategoria(1L);
            
            produtoService.inserir(produto);
            System.out.println("Produto cadastrado: " + produto);
            
            System.out.println("\nCASO DE USO - LISTAR PRODUTOS");
            ArrayList<Produto> listaProdutos = produtoService.listarTodos();
            System.out.println("Produtos cadastrados: " + listaProdutos);

            
            System.out.println("\nCASO DE USO - ATUALIZAR PRECO DO PRODUTO");
            produto.setPreco(new BigDecimal("42.90"));
            produtoService.atualizar(produto);
            System.out.println("Produto atualizado: " + produtoService.findById(produto.getId()));
            
        } catch (Exception exception) {
            System.out.println("Ocorreu um erro ao executar o sistema: " + exception.getMessage());
        }
        
    }

    
}

