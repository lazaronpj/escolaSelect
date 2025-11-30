package escolaSelect.br.com.escolaSelect.chatBot.view.telaPincipal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
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
import escolaSelect.br.com.escolaSelect.chatBot.util.CodigosReutilizaveisUI;

public class TelaCriarConta {

	private static JTextField campoNome;
	private static JTextField campoEmail;
	private static JPasswordField campoSenha, campoConfirmaSenha;
	private static JComboBox<String> comboTipo;

	public static void criarConta() {

		JFrame frame = new JFrame("Escola Select - Criar Conta");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 660);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		JPanel norte = new JPanel();
		norte.setLayout(new BoxLayout(norte, BoxLayout.Y_AXIS));
		norte.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 0));
		norte.add(Box.createRigidArea(new Dimension(0, 10)));
		norte.add(CodigosReutilizaveisUI.labelTitulo("<html>Criar Nova Conta - <b>Escola Select</b>!</html>"));
		norte.add(Box.createRigidArea(new Dimension(0, 10)));
		norte.add(CodigosReutilizaveisUI.labelNormal("Preencha os campos abaixo corretamente:"));
		norte.add(Box.createRigidArea(new Dimension(0, 20)));

		frame.add(norte, BorderLayout.NORTH);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

		campoNome = CodigosReutilizaveisUI.campoTexto("Digite seu nome completo");
		campoEmail = CodigosReutilizaveisUI.campoTexto("Digite seu e-mail");
		campoSenha = CodigosReutilizaveisUI.campoSenha("Crie uma senha");
		campoConfirmaSenha = CodigosReutilizaveisUI.campoSenha("Digite novamente sua senha para confirmar");
		comboTipo = new JComboBox<>(new String[]{"Selecione uma opção", "Aluno", "Professor"});
		comboTipo.setMaximumSize(new Dimension(340, 35));
		comboTipo.setFont(new Font("Arial", Font.PLAIN, 18));

		centro.add(CodigosReutilizaveisUI.labelNormal("Nome:"));
		centro.add(campoNome);
		centro.add(Box.createVerticalStrut(10));

		centro.add(CodigosReutilizaveisUI.labelNormal("E-mail:"));
		centro.add(campoEmail);
		centro.add(Box.createVerticalStrut(10));

		centro.add(CodigosReutilizaveisUI.labelNormal("<html>Senha <b>(No mínimo 6 caracteres)</b>:</html>"));
		centro.add(campoSenha);
		centro.add(Box.createVerticalStrut(10));

		centro.add(CodigosReutilizaveisUI.labelNormal("<html>Confirme sua senha <b>(No mínimo 6 caracteres)</b>:</html>"));
		centro.add(campoConfirmaSenha);
		centro.add(Box.createVerticalStrut(10));

		centro.add(CodigosReutilizaveisUI.labelNormal("Tipo:"));
		centro.add(comboTipo);
		centro.add(Box.createVerticalStrut(20));

		campoNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String txtNome = campoNome.getText().replaceAll("[^\\p{L}\\s'-]", "");

				if (txtNome.length() >= 35) {
					CodigosReutilizaveisUI.alerta("O campo nome deve possuir 35 caracteres alfabéticos ou menos!");
					campoNome.setText("");
				} else {
					campoNome.setText(txtNome);
				}
			}
		});

		campoEmail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String txtEmail = campoEmail.getText().trim();

				String txtFiltro = txtEmail.replaceAll("[^a-zA-Z0-9.@_+\\\\-]", "");

				if (txtEmail.length() > 30) {
					CodigosReutilizaveisUI.alerta("O campo e-mail deve possuir 30 caracteres ou menos!");
					txtEmail = txtEmail.substring(0, 30);
				} else {
					campoEmail.setText(txtFiltro);
				}

			}
		});

		campoSenha.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char[] txtSenha = campoSenha.getPassword();

				if (txtSenha.length >= 38) {
					CodigosReutilizaveisUI.alerta("O campo senha deve possuir 38 caracteres ou menos!");
					campoSenha.setText("");
				} else {
					campoSenha.setText(new String(txtSenha));
				}

				if (txtSenha.length < 6) {
					campoSenha.setBackground(CodigosReutilizaveisUI.corErroSenha);
					campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corErroBordaSenha, 2));
				} else {
					campoSenha.setBackground(CodigosReutilizaveisUI.corFoco);
					campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
				}
			}
		});

		campoConfirmaSenha.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char[] txtConfirmaSn = campoConfirmaSenha.getPassword();

				if (txtConfirmaSn.length >= 38) {
					CodigosReutilizaveisUI.alerta("O campo senha deve possuir 38 caracteres ou menos!");
					campoConfirmaSenha.setText("");
				} else {
					campoConfirmaSenha.setText(new String(txtConfirmaSn));
				}
			}
		});

		campoNome.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				campoNome.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoNome.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoNome.setBackground(CodigosReutilizaveisUI.corFoco);
				campoNome.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
			}
		});

		campoEmail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String email = campoEmail.getText().trim();
				if (!email.contains("@") || !email.contains(".")) {
					CodigosReutilizaveisUI.alerta("Por favor, insira um e-mail válido!");

				}
				campoEmail.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoEmail.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
			}

			@Override
			public void focusGained(FocusEvent e) {
				campoEmail.setBackground(CodigosReutilizaveisUI.corFoco);
				campoEmail.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
			}
		});

		campoSenha.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (campoSenha.getPassword().length < 6) {
					campoSenha.setBackground(CodigosReutilizaveisUI.corErroSenha);
					campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corErroBordaSenha, 2));
				} else {
					campoSenha.setBackground(CodigosReutilizaveisUI.corFoco);
					campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				campoSenha.setBackground(CodigosReutilizaveisUI.corDesfoco);
				if (campoSenha.getPassword().length < 6) {
					campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corErroBordaSenha, 2));
				} else {
					campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
				}
			}
		});

		campoConfirmaSenha.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				boolean confirmarSenha = CodigosReutilizaveisUI.validarSenha(campoSenha, campoConfirmaSenha);
				if (confirmarSenha) {
					campoConfirmaSenha.setBackground(CodigosReutilizaveisUI.corFoco);
					campoConfirmaSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
				} else {
					campoConfirmaSenha.setBackground(CodigosReutilizaveisUI.corErroSenha);
					campoConfirmaSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corErroBordaSenha, 2));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				boolean confirmarSenha = CodigosReutilizaveisUI.validarSenha(campoSenha, campoConfirmaSenha);
				if (confirmarSenha) {
					campoConfirmaSenha.setBackground(CodigosReutilizaveisUI.corFoco);
					campoConfirmaSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
				} else {
					campoConfirmaSenha.setBackground(CodigosReutilizaveisUI.corErroSenha);
					campoConfirmaSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corErroBordaSenha, 2));
				}
			}
		});

		comboTipo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				comboTipo.setBackground(CodigosReutilizaveisUI.corDesfoco);
				comboTipo.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
			}

			@Override
			public void focusGained(FocusEvent e) {
				comboTipo.setBackground(CodigosReutilizaveisUI.corFoco);
				comboTipo.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoFoco, 2));
			}
		});

		frame.add(centro, BorderLayout.CENTER);

		JPanel sul = new JPanel();
		sul.setLayout(new BoxLayout(sul, BoxLayout.Y_AXIS));

		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton botaoCriar = CodigosReutilizaveisUI.botao("Criar Conta", "Enviar dados");
		JButton botaoLimpar = CodigosReutilizaveisUI.botao("Limpar", "Limpar todos os campos");

		botoes.add(botaoCriar);
		botoes.add(botaoLimpar);

		botaoCriar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean confirma = CodigosReutilizaveisUI.validarSenha(campoSenha, campoConfirmaSenha);
				if (!confirma) {
					CodigosReutilizaveisUI.alerta("O campo 'confirmar senha' deve ser igual ao campo 'senha'");
					return;
				} else {
					if (!validarCampos()) {
						return;
					}
				}

				PessoaDTO ps = new PessoaDTO();
				PessoaDAO psd = new PessoaDAO();
				ps.setNome(campoNome.getText().trim());
				ps.setEmail(campoEmail.getText().trim());
				ps.setSenha(campoSenha.getPassword());
				ps.setTipo((String) comboTipo.getSelectedItem());

				if (psd.inserirCriarConta(ps)) {
					if (comboTipo.getSelectedItem().equals("Aluno")) {
						CodigosReutilizaveisUI.alertaSucesso("Conta criada com sucesso do aluno " + ps.getNome() + "!");
						limparCampos();
					} else {
						CodigosReutilizaveisUI.alertaSucesso("Conta criada com sucesso do professor " + ps.getNome() + "!");
						limparCampos();
					}
				} else {
					CodigosReutilizaveisUI.alertaErro("Erro ao criar conta " + ps.getNome() + "!");
				}

			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				campoSenha.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));
				campoConfirmaSenha.setBackground(CodigosReutilizaveisUI.corDesfoco);
				campoConfirmaSenha.setBorder(BorderFactory.createLineBorder(CodigosReutilizaveisUI.corBordaCampoDesfoco, 2));

			}
		});

		sul.add(botoes);
		sul.add(Box.createVerticalStrut(15));
		sul.add(CodigosReutilizaveisUI.criarCopyright());
		sul.add(Box.createVerticalStrut(10));

		frame.add(sul, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private static boolean validarCampos() {
		if (campoNome.getText().trim().isEmpty()) {
			CodigosReutilizaveisUI.alerta("O campo 'nome' não pode ficar vazio!");
			return false;
		}

		if (campoEmail.getText().trim().isEmpty()) {
			CodigosReutilizaveisUI.alerta("O campo 'e-mail' não pode ficar vazio!");
			return false;
		}

		if (campoSenha.getPassword().length < 6) {
			CodigosReutilizaveisUI.alerta("A senha deve ter no mínimo 6 caracteres!");
			return false;
		}

		if (comboTipo.getSelectedIndex() == 0) {
			CodigosReutilizaveisUI.alerta("O campo 'tipo' não pode ficar 'Selecione uma opção'!");
			return false;
		}
		return true;
	}

	private static void limparCampos() {
		campoNome.setText("");
		campoEmail.setText("");
		campoSenha.setText("");
		campoConfirmaSenha.setText("");
		comboTipo.setSelectedIndex(0);
	}
}