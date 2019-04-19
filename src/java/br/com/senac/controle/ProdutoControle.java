/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.controle;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.ProdutoDAO;
import br.com.senac.dao.ProdutoDAOImpl;
import br.com.senac.entidade.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dell
 */
@WebServlet(name = "CadastroControle", urlPatterns = {"/_cadastroControle"})
public class ProdutoControle extends HttpServlet {
    
        private HttpServletResponse response;
        private HttpServletRequest request;
        private Produto produto;         
        private ProdutoDAO produtoDAO;
        private Session session;
        private RequestDispatcher rd;
                
    protected void processarRequisicao()throws ServletException, IOException {
        String comando = request.getParameter("cmd");
        switch (comando){
            case "salvar":
                salvar();
                break;
        }
        rd.forward(request, response);
    }
    
    private void salvar(){
        produto = new Produto();
        produto.setNome(request.getParameter("nome"));
        produto.setQuantidade(Integer.parseInt(request.getParameter("qtde")));
        produto.setPrecoCompra(parseDouble(request.getParameter("compra")));
        produto.setPrecoVenda(parseDouble(request.getParameter("venda")));
        produto.setObservacao(request.getParameter("descricao"));
        produto.setCadastro(new Date());
        
        try {
            produtoDAO = new ProdutoDAOImpl();
            session = HibernateUtil.abreConexao();
            produtoDAO.salvarOuAlterar(produto, session);
            rd = request.getRequestDispatcher("produto.jsp");
        } catch (HibernateException e) {
            System.out.println("Erro ao salvar "+ e.getMessage());
        }
    }
    
    private void editar(){
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processarRequisicao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processarRequisicao();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
