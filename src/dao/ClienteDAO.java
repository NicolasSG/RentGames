package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;


/**
 * Classe de acesso a dados para a tabela 'clientes'.
 * @author Nicolas
 */
public class ClienteDAO {

    /**
     * Cadastra um cliente no banco de dados.
     * Mapeia os atributos do Java para as colunas do SQL.
     */
    public void cadastrar(Cliente cliente) {        
        String sql = "INSERT INTO clientes (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNomeCompleto());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getContato()); 
            stmt.setString(4, "Não informado"); 

            stmt.executeUpdate();
            System.out.println("Cliente " + cliente.getNomeCompleto() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    /**
     * Recupera todos os clientes da tabela.
     */
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {                
                Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"), 
                    rs.getString("cpf"),
                    "Endereço não cadastrado" 
                );
                clientes.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Remove um cliente pelo ID.
     */
    public void excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente ID " + id + " removido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }
    
    public List<Cliente> getClientes() {        
        try {
            dao.ClienteDAO clienteDAO = new dao.ClienteDAO();
            return clienteDAO.listarTodos();
        } catch (Exception e) {
            System.err.println("Erro ao buscar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}