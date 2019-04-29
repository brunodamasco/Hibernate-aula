/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public class TestaCliente {
    
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private Session session;
    
    public static void main(String[] args) {
        TestaCliente testaCliente = new TestaCliente();
        testaCliente.testSalvar();
    }
    
     public void testSalvar() {
        System.out.println("teste salvar");
        cliente = new Cliente(null, "Cliente Teste", "999.999.999-99", "email@email.com", "(99)99999-9999", "Gerente Comercial");
        session = HibernateUtil.abreConexao();
        
        clienteDAO = new ClienteDAOImpl();

        clienteDAO.salvarOuAlterar(cliente, session);
        session.close();
    }
}
