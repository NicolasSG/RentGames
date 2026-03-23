package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;


public class JogoDAO {

    public void cadastrar(model.Jogo jogo) throws SQLException {    
        String sql = "INSERT INTO jogos (nome, ano, plataforma, valor_aluguel, dias_aluguel, estoque) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogo.getNome());
            stmt.setInt(2, jogo.getAno());
            stmt.setString(3, jogo.getPlataforma());
            stmt.setDouble(4, jogo.getValorAluguel());
            stmt.setInt(5, jogo.getDiasAluguelPadrao());
            stmt.setInt(6, jogo.getQuantidadeEstoque());

            stmt.executeUpdate();
        }
    }

    public List<Jogo> listarTodos() {
        List<Jogo> lista = new ArrayList<>();
        String sql = "SELECT * FROM jogos";    

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Jogo j = new Jogo(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("ano"),
                    rs.getString("plataforma"),
                    rs.getDouble("valor_aluguel"),
                    rs.getInt("dias_aluguel"),
                    rs.getInt("estoque")
                );
                lista.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Erro SQL ao listar: " + e.getMessage());            
        }
        return lista;
    }
    
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM jogos WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}