/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.util.Gerador;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dell
 */
public class ClienteDAOImplTest {
    
    private Session session;
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    
    public ClienteDAOImplTest() {
    }

    @Test
    public void testSalvar() {
        System.out.println("teste salvar");
        cliente = new Cliente(null, "Cliente Teste1" + Gerador.gerarNome(5), 
                "000.000.000-00" + 1, "email@email.com.br" + 1, "(48)99999-9999", "profissão: " + Gerador.gerarNome(20));
        session = HibernateUtil.abreConexao();
        clienteDAO.salvarOuAlterar(cliente, session);
        session.close();
        assertNotNull(cliente.getId());
    }
   
    //@Test
    public void testListarPorNome() {
        System.out.println("listarPorNome");
        String nome = "";
        Session sessao = null;
        ClienteDAOImpl instance = new ClienteDAOImpl();
        List<Cliente> expResult = null;
        List<Cliente> result = instance.listarPorNome(nome, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        Long id = null;
        Session session = null;
        ClienteDAOImpl instance = new ClienteDAOImpl();
        Cliente expResult = null;
        Cliente result = instance.pesquisarPorId(id, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
