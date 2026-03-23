package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Aluguel;
import model.Jogo;


/**
 * @author Nicolas
 */
public class AluguelDAO {

    public void registrarAluguel(Aluguel aluguel) {    
        String sqlAluguel = "INSERT INTO alugueis (nome_cliente, contato, cpf, endereco, id_jogo, dias_aluguel, data_inicio, devolvido) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlEstoque = "UPDATE jogos SET estoque = estoque - 1 WHERE id = ?";

        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false); 

            // 1. Insere o Aluguel
            try (PreparedStatement stmtA = conn.prepareStatement(sqlAluguel)) {
                stmtA.setString(1, aluguel.getNomeCliente()); 
                stmtA.setString(2, aluguel.getContato());     
                stmtA.setString(3, aluguel.getCpf());         
                stmtA.setString(4, aluguel.getEndereco());    
                stmtA.setInt(5, aluguel.getJogo().getId());
                stmtA.setInt(6, aluguel.getDiasAluguel());
                stmtA.setDate(7, Date.valueOf(aluguel.getDataInicio()));
                stmtA.setBoolean(8, false); // Coluna devolvido inicia como false

                stmtA.executeUpdate();
            }

            // 2. Atualiza Estoque (Aqui usamos o nome correto da coluna: 'estoque')
            try (PreparedStatement stmtE = conn.prepareStatement(sqlEstoque)) {
                stmtE.setInt(1, aluguel.getJogo().getId());
                stmtE.executeUpdate();
            }

            conn.commit(); 
            System.out.println("Aluguel registrado com sucesso!");
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { }
            System.err.println("Erro ao registrar aluguel: " + e.getMessage());
        }
    }

    public List<Aluguel> listarTodos() {
        List<Aluguel> lista = new ArrayList<>();              
        String sql = "SELECT a.*, j.nome as nome_jogo, j.valor_aluguel FROM alugueis a " +
             "JOIN jogos j ON a.id_jogo = j.id ORDER BY a.id DESC";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {                
                Jogo j = new Jogo(rs.getInt("id_jogo"), rs.getString("nome_jogo"), 0, "", rs.getDouble("valor_aluguel"), 0, 0);
                
                Aluguel a = new Aluguel(
                    rs.getInt("id"),
                    rs.getString("nome_cliente"),
                    rs.getString("contato"),
                    rs.getString("cpf"),
                    rs.getString("endereco"),
                    j,
                    rs.getInt("dias_aluguel"),
                    rs.getDate("data_inicio").toLocalDate()
                );
                a.setDevolvido(rs.getBoolean("devolvido"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void registrarDevolucao(int idAluguel, int idJogo) {
        String sqlStatus = "UPDATE alugueis SET devolvido = true WHERE id = ?";
        String sqlEstoque = "UPDATE jogos SET estoque = estoque + 1 WHERE id = ?";

        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(sqlStatus)) {
                stmt1.setInt(1, idAluguel);
                stmt1.executeUpdate();
            }
            try (PreparedStatement stmt2 = conn.prepareStatement(sqlEstoque)) {
                stmt2.setInt(1, idJogo);
                stmt2.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { }
        }
    }
}