package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Transacao;
import dao.*;


public class TransacaoDAO {
    public void cadastrar(Transacao t) {        
        String sql = "INSERT INTO transacoes (descricao, tipo, valor, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getDescricao());
            stmt.setString(2, t.getTipo().name());
            stmt.setDouble(3, t.getValor());
            stmt.setString(4, t.getData());

            int linhasAfetadas = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro SQL ao cadastrar transação: " + e.getMessage());
        }
    }
    
    public List<Transacao> listarTodas() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes ORDER BY id DESC";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transacao t = new Transacao(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    Transacao.Tipo.valueOf(rs.getString("tipo")),
                    rs.getDouble("valor"),
                    rs.getString("data")
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public double calcularSaldo() {
        double entradas = 0;
        double saidas = 0;
        String sql = "SELECT tipo, SUM(valor) as total FROM transacoes GROUP BY tipo";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                if (rs.getString("tipo").equals("ENTRADA")) entradas = rs.getDouble("total");
                else saidas = rs.getDouble("total");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return entradas - saidas;
    }
    
    public void salvar(model.Transacao t) {
        String sql = "INSERT INTO transacoes (descricao, tipo, valor, data) VALUES (?, ?, ?, ?)";
                
        try (Connection conn = Conexao.getConexao(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {             
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
