package escolaSelect.br.com.escolaSelect.chatBot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import escolaSelect.br.com.escolaSelect.chatBot.bd.Conexao;
import escolaSelect.br.com.escolaSelect.chatBot.dto.MateriaDTO;

public class MateriaDAO {

	public boolean inserir(MateriaDTO mt) {
		String sql = "INSERT INTO materia (nome, nota_prova, nota_trabalho, nota_ponto, faltas) VALUES (?, ?, ?, ?, ?)";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(0, mt.getNome());
			stmt.setDouble(1, mt.getNotaProva());
			stmt.setDouble(2, mt.getNotaTrabalho());
			stmt.setDouble(3, mt.getNotaPonto());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			alertaErro("Erro ao inserir dados da matéria " + mt.getNome() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	public MateriaDTO buscarPorId(int idMateria) {
		String sql = "SELECT * FROM materia WHERE id = ?";
		MateriaDTO mt = null;

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setInt(1, idMateria);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				mt = new MateriaDTO();
				mt.setNome(rs.getString("nome"));
				mt.setNotaProva(rs.getDouble("nota_prova"));
				mt.setNotaTrabalho(rs.getDouble("nota_trabalho"));
				mt.setNotaPonto(rs.getDouble("nota_ponto"));
				mt.setFaltas(rs.getInt("faltas"));
			}
		} catch (SQLException e) {
			alertaErro("Erro ao buscar ID da matéria " + mt.getId() + " no banco de dados!");
			e.printStackTrace();
		}
		return mt;
	}

	public boolean atualizar(MateriaDTO mt) {
		String sql = "UPDATE materia SET nome = ?, nota_prova = ?, nota_trabalho = ?, nota_pontos = ?, faltas = ? WHERE id = ?";

		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(0, mt.getNome());
			stmt.setDouble(1, mt.getNotaProva());
			stmt.setDouble(2, mt.getNotaTrabalho());
			stmt.setDouble(3, mt.getNotaPonto());
			stmt.setInt(4, mt.getFaltas());

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			alertaErro("Erro ao atualizar a matéria com nome " + mt.getNome() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	public boolean remover(int id) {
		String sql = "DELETE FROM materia WHERE id = ?";
		MateriaDTO mt = new MateriaDTO();
		try (Connection conexao = new Conexao().conectarBD(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			alertaErro("Erro ao remover a pessoa com nome " + mt.getNome() + " no banco de dados!");
			e.printStackTrace();
			return false;
		}
	}

	private void alertaErro(String msg) {
		JOptionPane.showMessageDialog(null, "<html>❌ <b><font color='red'>Erro:</font></b> " + msg + "</html>", "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
