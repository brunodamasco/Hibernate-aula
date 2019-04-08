/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Produto;
import java.util.Date;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public class TestaProduto {
    
    private Produto produto;
    private ProdutoDAO produtoDAO;
    private Session session;
    
    public static void main(String[] args) {
        TestaProduto testaProduto = new TestaProduto();
        testaProduto.testSalvar();
    }
    
     public void testSalvar() {
        System.out.println("teste salvar");
        produto = new Produto(null, "Produto Teste", 10, 10, 0, new Date(), "bla, bla...");
        session = HibernateUtil.abreConexao();
        
        produtoDAO = new ProdutoDAOImpl();

        produtoDAO.salvarOuAlterar(produto, session);
        session.close();
    }
}
