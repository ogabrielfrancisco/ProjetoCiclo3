package acesso_a_dado;

import negocio.Conta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private final String url = "jdbc:postgresql://localhost/banco";
    private final String user = "postgres";
    private final String password = "postgres";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean criarConta(Conta conta) {
        String sql = "INSERT INTO public.conta (numero, saldo) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, conta.getNumero());
            pstmt.setDouble(2, conta.getSaldo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarSaldo(String numero, double saldo) {
        String sql = "UPDATE public.conta SET saldo = ? WHERE numero = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, saldo);
            pstmt.setString(2, numero);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Conta> listarContas() {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT numero, saldo FROM public.conta";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Conta conta = new Conta(rs.getString("numero"), rs.getDouble("saldo"));
                contas.add(conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }
}
