package br.com.senac.dao;

import br.com.senac.entidade.Produto;
import br.com.senac.util.Gerador;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell
 */
public class ProdutoDAOImplTest {
    private Produto produto;
    private ProdutoDAO produtoDAO;
    private Session session;
    
    public ProdutoDAOImplTest() {
        produtoDAO = new ProdutoDAOImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("teste salvat");
        produto = new Produto(null, "Produto Teste1" + Gerador.gerarNome(5), 
                10, 10, 0, new Date(), "bla, bla...");
        session = HibernateUtil.abreConexao();
        produtoDAO.salvarOuAlterar(produto, session);
        session.close();
        assertNotNull(produto.getId());
    }
    
    @Test
    public void testAlterar() {
        System.out.println("alterar...");
        produto = new Produto(1L, "Alterado", 10, 10, 20, new Date(), "Ateração");
        
        session = HibernateUtil.abreConexao();
        produtoDAO.salvarOuAlterar(produto, session);
        session.close();
    }
    
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        produto.setId(1L);
        session = HibernateUtil.abreConexao();
        produtoDAO.excluir(produto, session);
        session.close();
    }
    
    @Test
    public void testListarPorNome() {
        System.out.println("listarPorNome");
    }
}
