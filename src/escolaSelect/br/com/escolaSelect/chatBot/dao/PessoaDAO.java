package escolaSelect.br.com.escolaSelect.chatBot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import escolaSelect.br.com.escolaSelect.chatBot.bd.Conexao;
import escolaSelect.br.com.escolaSelect.chatBot.dto.PessoaDTO;

public class PessoaDAO {

	public boolean inserirCriarConta(PessoaDTO ps) {
		String sql = "INSERT INTO pessoa (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, ps.getNome());
			stmt.setString(2, ps.getEmail());
			stmt.setString(3, new String(ps.getSenha()));
			stmt.setString(4, ps.getTipo());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			alertaErro("Erro ao inserir usuário do tipo " + ps.getTipo() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	public PessoaDTO buscarPorId(int idPessoa) {
		String sql = "SELECT * FROM pessoa WHERE id = ?";
		PessoaDTO ps = null;

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, idPessoa);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				ps = new PessoaDTO();
				ps.setId(rs.getInt("id"));
				ps.setNome(rs.getString("nome"));
				ps.setEmail(rs.getString("email"));
				ps.setSenha(rs.getString("senha").toCharArray());
				ps.setTipo(rs.getString("tipo"));
			}

		} catch (SQLException e) {
			alertaErro("Erro ao buscar ID " + idPessoa + " no banco de dados!");
			e.printStackTrace();
		}

		return ps;
	}

	public boolean fazerLogin(String email, String senha) {
		String sql = "SELECT * FROM pessoa WHERE email = ? AND senha = ?";

		boolean loginSucesso = false;

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				loginSucesso = true;
			}

		} catch (SQLException e) {
			alertaErro("Login ou senha incorretos!");
			e.printStackTrace();
		}
		return loginSucesso;
	}

	public boolean verificarEmail(String email) {
		String sql = "SELECT * FROM pessoa WHERE email = ?";
		boolean emailCorreto = false;

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				emailCorreto = true;
			}

		} catch (SQLException e) {
			alertaErro("Login ou senha incorretos!");
			e.printStackTrace();
		}
		return emailCorreto;
	}

	public boolean atualizarConta(PessoaDTO ps) {
		String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id = ?";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, ps.getNome());
			stmt.setString(2, ps.getEmail());
			stmt.setString(3, new String(ps.getSenha()));
			stmt.setString(4, ps.getTipo());
			stmt.setInt(5, ps.getId());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			alertaErro("Erro ao atualizar a pessoa " + ps.getNome() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	public boolean removerConta(int id) {
		String sql = "DELETE FROM pessoa WHERE id = ?";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			alertaErro("Erro ao remover a pessoa com ID " + id + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	private void alertaErro(String msg) {
		JOptionPane.showMessageDialog(null, "<html>❌ <b><font color='red'>Erro:</font></b> " + msg + "</html>", "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
