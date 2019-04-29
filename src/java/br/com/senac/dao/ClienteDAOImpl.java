/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public class ClienteDAOImpl extends BaseDAOImpl<Cliente, Long>
    implements ClienteDAO{

    @Override
    public List<Cliente> listarPorNome(String nome, Session sessao) 
                throws HibernateException {
        Query consulta = sessao.createQuery("from Cliente c where c.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
    @Override
    public Cliente pesquisarPorId(Long id, Session session) throws HibernateException {
        Cliente cliente = (Cliente) session.get(Cliente.class, id);
        return cliente;
    }
    
}
