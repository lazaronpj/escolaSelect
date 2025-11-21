package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import escolaSelect.br.com.escolaSelect.chatBot.dao.PessoaDAO;
import escolaSelect.br.com.escolaSelect.chatBot.dto.PessoaDTO;

public class TelaCriarConta {

	private static JTextField campoNome;
	private static JTextField campoEmail;
	private static JPasswordField campoSenha;
	private static JComboBox<String> comboTipo;

	public static void criarConta() {

		JFrame frame = new JFrame("Criar Conta");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		JPanel norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));
		norte.add(TelaLogin.labelTitulo("<html><div style='text-align:center;'>Criar Nova Conta</div></html>"));
		norte.add(Box.createRigidArea(new Dimension(0, 10)));
		norte.add(TelaLogin.labelNormal("Preencha as informações abaixo:"));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));

		frame.add(norte, BorderLayout.NORTH);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		campoNome = TelaLogin.campoTexto("Digite seu nome completo");
		campoEmail = TelaLogin.campoTexto("Digite seu e-mail");
		campoSenha = TelaLogin.campoSenha("Crie uma senha");
		comboTipo = new JComboBox<>(new String[]{"Selecione uma opção", "Aluno", "Professor"});
		comboTipo.setMaximumSize(new Dimension(340, 35));
		comboTipo.setFont(new Font("Arial", Font.PLAIN, 18));

		centro.add(TelaLogin.labelNormal("Nome:"));
		centro.add(campoNome);
		centro.add(Box.createVerticalStrut(10));

		centro.add(TelaLogin.labelNormal("E-mail:"));
		centro.add(campoEmail);
		centro.add(Box.createVerticalStrut(10));

		centro.add(TelaLogin.labelNormal("Senha:"));
		centro.add(campoSenha);
		centro.add(Box.createVerticalStrut(10));

		centro.add(TelaLogin.labelNormal("Tipo:"));
		centro.add(comboTipo);
		centro.add(Box.createVerticalStrut(20));

		frame.add(centro, BorderLayout.CENTER);

		JPanel sul = new JPanel();
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));

		JButton botaoCriar = TelaLogin.botao("Criar Conta", "Enviar dados");
		JButton botaoLimpar = TelaLogin.botao("Limpar", "Limpar todos os campos");

		botaoCriar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(Arrays.toString(campoSenha.getPassword()));

				if (!validarCampos()) {
					return;
				}
				PessoaDTO ps = new PessoaDTO();
				PessoaDAO psd = new PessoaDAO();
				ps.setNome(campoNome.getText().trim());
				ps.setEmail(campoEmail.getText().trim());
				ps.setSenha(campoSenha.getPassword());
				ps.setTipo((String) comboTipo.getSelectedItem());

				if (psd.inserir(ps)) {
					if (comboTipo.getSelectedItem().equals("Aluno")) {
						TelaLogin.alertaSucesso("Conta criada com sucesso do aluno " + ps.getNome() + "!");
					} else {
						TelaLogin.alertaSucesso("Conta criada com sucesso do professor " + ps.getNome() + "!");
					}
				} else {
					TelaLogin.alertaErro("Erro ao criar conta " + ps.getNome() + "!");

				}

			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

		sul.add(botaoCriar);
		sul.add(Box.createVerticalStrut(8));
		sul.add(botaoLimpar);
		sul.add(Box.createVerticalStrut(15));
		sul.add(TelaLogin.criarCopyright());
		sul.add(Box.createVerticalStrut(10));

		frame.add(sul, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private static boolean validarCampos() {
		if (campoNome.getText().trim().isEmpty()) {
			TelaLogin.alerta("O campo 'nome' não pode ficar vazio!");
			return false;
		}

		if (campoEmail.getText().trim().isEmpty()) {
			TelaLogin.alerta("O campo 'e-mail' não pode ficar vazio!");
			return false;
		}

		if (campoSenha.getPassword().length == 0) {
			TelaLogin.alerta("O campo 'senha' não pode ficar vazio!");
			return false;
		}

		if (comboTipo.getSelectedIndex() == 0) {
			TelaLogin.alerta("O campo 'tipo' não pode ficar 'Selecione uma opção'!");
			return false;
		}
		return true;
	}
	private static void limparCampos() {
		campoNome.setText("");
		campoEmail.setText("");
		campoSenha.setText("");
		comboTipo.setSelectedIndex(0);
	}
}
