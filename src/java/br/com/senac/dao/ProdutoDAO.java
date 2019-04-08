/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public interface ProdutoDAO extends BaseDAO<Produto, Long>{
    List<Produto> listarPorNome(String nome, Session sessao)
            throws HibernateException;
}
