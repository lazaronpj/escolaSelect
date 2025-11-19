package escolaSelect.br.com.escolaSelect.chatBot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import escolaSelect.br.com.escolaSelect.chatBot.bd.Conexao;
import escolaSelect.br.com.escolaSelect.chatBot.dto.PessoaDTO;

public class PessoaDAO {

	public boolean inserir(PessoaDTO ps) {
		String sql = "INSERT INTO pessoa (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(0, ps.getNome());
			stmt.setString(1, ps.getEmail());
			stmt.setString(2, ps.getSenha());
			stmt.setString(3, ps.getTipo());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			alertaErro("Erro ao inserir usuario do tipo " + ps.getTipo() + "no banco de dados!");
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
				ps.setNome(rs.getString("nome"));
				ps.setEmail(rs.getString("email"));
				ps.setSenha(rs.getString("senha"));
				ps.setTipo(rs.getString("tipo"));
			}
		} catch (SQLException e) {
			alertaErro("Erro ao buscar ID " + ps.getId() + " no banco de dados!");
			e.printStackTrace();
		}
		return ps;
	}

	public boolean atualizar(PessoaDTO ps) {
		String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id = ?";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(0, ps.getNome());
			stmt.setString(1, ps.getEmail());
			stmt.setString(2, ps.getSenha());
			stmt.setString(3, ps.getTipo());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			alertaErro("Erro ao atualizar a pessoa com nome " + ps.getNome() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	public boolean remover(int id) {
		String sql = "DELETE FROM pessoa WHERE id = ?";
		PessoaDTO ps = new PessoaDTO();
		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			alertaErro("Erro ao remover a pessoa com nome " + ps.getNome() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	private void alertaErro(String msg) {
		JOptionPane.showMessageDialog(null, "<html>❌ <b><font color='red'>Erro:</font></b> " + msg + "</html>", "Erro", JOptionPane.ERROR_MESSAGE);
	}

}
