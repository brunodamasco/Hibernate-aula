/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.controle;

import br.com.senac.dao.ClienteDAO;
import br.com.senac.dao.ClienteDAOImpl;
import br.com.senac.dao.HibernateUtil;
import br.com.senac.entidade.Cliente;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "ClienteControle", urlPatterns = {"/_clienteControle"})
public class ClienteControle extends HttpServlet {
    
        private HttpServletResponse response;
        private HttpServletRequest request;
        private Cliente cliente;         
        private ClienteDAO clienteDAO;
        private Session session;
        private RequestDispatcher rd;
                
    protected void processarRequisicao()throws ServletException, IOException {
        String comando = request.getParameter("cmd");
        switch (comando){
            case "salvar":
                salvar();
                break;
            case "pesquisarPorNome":
                pesquisarPorNome();
                break;
            case "excluir":
                excluir();
                break;
            case "alterar":
                pesquisarPeloId();
                break;
        }
        rd.forward(request, response);
    }
    
    private void pesquisarPeloId(){
        Long id = Long.parseLong(request.getParameter("id"));
        session = HibernateUtil.abreConexao();
        clienteDAO = new ClienteDAOImpl();
        try {
            cliente = clienteDAO.pesquisarPorId(id, session);
            session.close();
            request.setAttribute("cliAlterar", cliente);
            rd = request.getRequestDispatcher("cliente.jsp");
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar por Id controle" + e.getMessage());
        }
    }
    
    private void pesquisarPorNome() {
        String nome = request.getParameter("nome");
        clienteDAO = new ClienteDAOImpl();
        try {
            session = HibernateUtil.abreConexao();
            List<Cliente> clientes = clienteDAO.listarPorNome(nome, session);
            session.close();
            
            if(clientes.isEmpty()){
                request.setAttribute("msg", "Não existe clientes com esse valor.");
            }
            request.setAttribute("cli", clientes);
            rd = request.getRequestDispatcher("pesquisarCliente.jsp");
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar Cliente " + e.getMessage());
        }
    }
    private void salvar(){
        cliente = new Cliente();
        
        String msg = "Salvo com Sucesso!";
        String id = request.getParameter("id");
        if(!id.equals("")){
            cliente.setId(Long.parseLong(id));
            msg = "Alterado com Sucesso!";
        }

        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf((request.getParameter("cpf")));
        cliente.setEmail((request.getParameter("email")));
        cliente.setTelefone((request.getParameter("telefone")));
        cliente.setProfissao(request.getParameter("profissao"));
        
        try {
            clienteDAO = new ClienteDAOImpl();
            session = HibernateUtil.abreConexao();
            clienteDAO.salvarOuAlterar(cliente, session);
            rd = request.getRequestDispatcher("cliente.jsp");
            request.setAttribute("msg", msg);
        } catch (HibernateException e) {
            System.out.println("Erro ao salvar "+ e.getMessage());
        }
    }
    
    private void excluir(){
        Long id = Long.parseLong(request.getParameter("id"));
        session = HibernateUtil.abreConexao();
        clienteDAO = new ClienteDAOImpl();
        cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(" ");
        try {
            clienteDAO.excluir(cliente, session);
            request.setAttribute("mens", "Excluído com sucesso!");
            session.close();
        } catch (Exception e) {
            System.out.println("Erro no controle ao excluir " + e.getMessage());
        }
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
