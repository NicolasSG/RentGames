package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;


/**
 * @author Nicolas
 */
public class UsuarioDAO {

    public void cadastrar(Usuario usuario) {
    String sql = "INSERT INTO usuarios (nome, email, senha, contato, cpf, endereco, perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNomeCompleto());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getContato());
        stmt.setString(5, usuario.getCpf());
        stmt.setString(6, usuario.getEndereco());
        stmt.setString(7, usuario.getNivelPermissao().name());

        stmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar no banco: " + e.getMessage());
    }
}

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getString("contato"),
                rs.getString("cpf"),
                rs.getString("endereco"),                
                Usuario.NivelPermissao.valueOf(rs.getString("perfil").toUpperCase())
            );
                
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir: " + e.getMessage());
        }
    }
    
    public model.Usuario autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {                
                    return new model.Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("contato"),
                        rs.getString("cpf"),
                        rs.getString("endereco"),
                        model.Usuario.NivelPermissao.valueOf(rs.getString("perfil"))
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
        }
        return null;
    }
}