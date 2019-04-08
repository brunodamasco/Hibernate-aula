/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public class ProdutoDAOImpl extends BaseDAOImpl<Produto, Long>
    implements ProdutoDAO{

    @Override
    public List<Produto> listarPorNome(String nome, Session sessao) 
            throws HibernateException {
        Query consulta = sessao.createQuery("from Produto p where p.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public Produto pesquisarPorId(Long id, Session session) throws HibernateException {
        Produto produto = (Produto) session.get(Produto.class, id);
        return produto;
    }
    
}
