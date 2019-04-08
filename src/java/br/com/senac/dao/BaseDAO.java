/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
public interface BaseDAO<T, ID> {
    void salvarOuAlterar(T entidade, Session session) 
            throws HibernateException;
    void excluir(T entidade, Session session) 
            throws HibernateException;

    T pesquisarPorId(Long id, Session session)
            throws HibernateException;
}
