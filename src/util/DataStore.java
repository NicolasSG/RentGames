package util;

import java.sql.SQLException;
import model.Usuario;
import java.util.List;
import model.*;

public class DataStore {
    
    private static DataStore instance;
    private Usuario usuarioLogado;    
    
    private dao.TransacaoDAO transacaoDAO = new dao.TransacaoDAO();
    private dao.AluguelDAO aluguelDAO = new dao.AluguelDAO();
    private dao.JogoDAO jogoDAO = new dao.JogoDAO();    
    

    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }
    
    public List<Transacao> getTransacoes() {
        dao.TransacaoDAO dao = new dao.TransacaoDAO();
        return dao.listarTodas();
    }

    public double getSaldoCaixa() {
        return transacaoDAO.calcularSaldo();
    }
    
    public List<Jogo> getJogos() {
        return jogoDAO.listarTodos();
    }

    public List<Aluguel> getAlugueis() {
        return aluguelDAO.listarTodos();
    }
   
    public Usuario getUsuarioLogado() { return usuarioLogado; }
    public void setUsuarioLogado(Usuario u) { this.usuarioLogado = u; }
    
    public void addAluguel(Aluguel a) {
        try {            
            dao.AluguelDAO aluguelDAO = new dao.AluguelDAO();
            aluguelDAO.registrarAluguel(a);
        } catch (Exception e) {
            System.err.println("Erro ao salvar aluguel no banco: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao salvar aluguel: " + e.getMessage());
        }
    }
    
    public void addTransacao(model.Transacao t) {
        try {
            dao.TransacaoDAO dao = new dao.TransacaoDAO();
            dao.cadastrar(t);
        } catch (Exception e) {
            System.err.println("Erro ao salvar transação no DataStore: " + e.getMessage());
        }
    }
    
    public List<model.Cliente> getClientes() {
        try {            
            dao.ClienteDAO clienteDAO = new dao.ClienteDAO();
            return clienteDAO.listarTodos();
        } catch (Exception e) {            
            System.err.println("Erro ao buscar clientes no DataStore: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
    
    public model.Usuario autenticar(String email, String senha) {
        dao.UsuarioDAO usuarioDAO = new dao.UsuarioDAO();
        model.Usuario user = usuarioDAO.autenticar(email, senha);
       
        if (user != null) {
            this.usuarioLogado = user; 
        }

        return user;
    }
    
    public List<model.Usuario> getUsuarios() {
        try {            
            dao.UsuarioDAO usuarioDAO = new dao.UsuarioDAO();
            return usuarioDAO.listarTodos();
        } catch (Exception e) {            
            System.err.println("Erro ao buscar usuários no DataStore: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
    
    public void addUsuario(Usuario u) {
        try {
            dao.UsuarioDAO usuarioDAO = new dao.UsuarioDAO();
            usuarioDAO.cadastrar(u);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar usuário no DataStore: " + e.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Erro ao salvar usuário: " + e.getMessage());
        }
    }
}